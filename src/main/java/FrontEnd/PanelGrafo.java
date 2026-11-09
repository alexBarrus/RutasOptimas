package FrontEnd;

import BackEnd.Arista;
import BackEnd.Grafo;
import BackEnd.Vertice;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/** Dibuja las ubicaciones en círculo y sus rutas con el peso de cada una. */
public class PanelGrafo extends JPanel {

    private static final int DIAMETRO = 46;
    private static final Color COLOR_NODO = new Color(38, 99, 168);
    private static final Color COLOR_RUTA = new Color(90, 100, 112);
    private static final Color COLOR_TEXTO = new Color(35, 40, 48);
    private static final Color COLOR_CAMINO = new Color(204, 62, 38);

    private Grafo grafo;
    private List<String> camino = List.of();

    public PanelGrafo() {
        setBackground(Color.WHITE);
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
        repaint();
    }

    /** Define la secuencia de ubicaciones que se resalta como camino óptimo. */
    public void setCamino(List<String> camino) {
        this.camino = camino;
        repaint();
    }

    private boolean estaEnCamino(Arista arista) {
        for (int i = 0; i + 1 < camino.size(); i++) {
            String a = camino.get(i);
            String b = camino.get(i + 1);
            if ((arista.getOrigen().equals(a) && arista.getDestino().equals(b))
                    || (!grafo.esDirigido() && arista.getOrigen().equals(b) && arista.getDestino().equals(a))) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (grafo == null || grafo.estaVacio()) {
            g2.setColor(COLOR_RUTA);
            dibujarCentrado(g2, grafo == null ? "Cree un grafo para comenzar" : "Grafo vacío",
                    getWidth() / 2.0, getHeight() / 2.0);
        } else {
            Map<String, Point2D.Double> posiciones = calcularPosiciones(grafo.getVertices());
            for (Arista arista : grafo.getAristas()) {
                dibujarArista(g2, arista, posiciones);
            }
            for (Vertice vertice : grafo.getVertices()) {
                dibujarVertice(g2, vertice, posiciones.get(vertice.getId()));
            }
        }
        g2.dispose();
    }

    private Map<String, Point2D.Double> calcularPosiciones(List<Vertice> vertices) {
        Map<String, Point2D.Double> posiciones = new HashMap<>();
        double cx = getWidth() / 2.0;
        double cy = getHeight() / 2.0;
        double radio = vertices.size() == 1 ? 0 : Math.max(10, Math.min(cx, cy) - DIAMETRO);
        for (int i = 0; i < vertices.size(); i++) {
            double angulo = -Math.PI / 2 + 2 * Math.PI * i / vertices.size();
            posiciones.put(vertices.get(i).getId(), new Point2D.Double(
                    cx + radio * Math.cos(angulo), cy + radio * Math.sin(angulo)));
        }
        return posiciones;
    }

    private void dibujarArista(Graphics2D g2, Arista arista, Map<String, Point2D.Double> posiciones) {
        Point2D.Double a = posiciones.get(arista.getOrigen());
        Point2D.Double b = posiciones.get(arista.getDestino());
        double distancia = a.distance(b);
        if (distancia < DIAMETRO) {
            return;
        }
        double ux = (b.x - a.x) / distancia;
        double uy = (b.y - a.y) / distancia;
        // Separa visualmente las rutas de ida y vuelta entre las mismas ubicaciones.
        double desplazamiento = tieneRutaInversa(arista) ? 8 : 0;
        double nx = -uy * desplazamiento;
        double ny = ux * desplazamiento;
        double margen = DIAMETRO / 2.0 + 2;
        double x1 = a.x + ux * margen + nx;
        double y1 = a.y + uy * margen + ny;
        double x2 = b.x - ux * margen + nx;
        double y2 = b.y - uy * margen + ny;

        boolean enCamino = estaEnCamino(arista);
        g2.setColor(enCamino ? COLOR_CAMINO : COLOR_RUTA);
        g2.setStroke(new BasicStroke(enCamino ? 3.2f : 1.6f));
        g2.draw(new java.awt.geom.Line2D.Double(x1, y1, x2, y2));
        if (grafo.esDirigido()) {
            dibujarPunta(g2, x2, y2, ux, uy);
        }
        dibujarPeso(g2, Grafo.formatear(arista.getPeso()), x1 + (x2 - x1) * 0.4, y1 + (y2 - y1) * 0.4);
    }

    private boolean tieneRutaInversa(Arista arista) {
        if (!grafo.esDirigido()) {
            return false;
        }
        for (Arista otra : grafo.getAristas()) {
            if (otra.getOrigen().equals(arista.getDestino()) && otra.getDestino().equals(arista.getOrigen())) {
                return true;
            }
        }
        return false;
    }

    private void dibujarPunta(Graphics2D g2, double x, double y, double ux, double uy) {
        double largo = 11;
        double ancho = 5;
        Path2D.Double punta = new Path2D.Double();
        punta.moveTo(x, y);
        punta.lineTo(x - ux * largo - uy * ancho, y - uy * largo + ux * ancho);
        punta.lineTo(x - ux * largo + uy * ancho, y - uy * largo - ux * ancho);
        punta.closePath();
        g2.fill(punta);
    }

    private void dibujarPeso(Graphics2D g2, String peso, double x, double y) {
        FontMetrics fm = g2.getFontMetrics();
        int ancho = fm.stringWidth(peso) + 8;
        int alto = fm.getHeight();
        g2.setColor(getBackground());
        g2.fillRect((int) (x - ancho / 2.0), (int) (y - alto / 2.0), ancho, alto);
        g2.setColor(COLOR_TEXTO);
        dibujarCentrado(g2, peso, x, y);
    }

    private void dibujarVertice(Graphics2D g2, Vertice vertice, Point2D.Double p) {
        int x = (int) Math.round(p.x - DIAMETRO / 2.0);
        int y = (int) Math.round(p.y - DIAMETRO / 2.0);
        g2.setColor(camino.contains(vertice.getId()) ? COLOR_CAMINO : COLOR_NODO);
        g2.fillOval(x, y, DIAMETRO, DIAMETRO);
        g2.setColor(Color.WHITE);
        dibujarCentrado(g2, recortar(vertice.getId(), 6), p.x, p.y);
        g2.setColor(COLOR_TEXTO);
        dibujarCentrado(g2, recortar(vertice.getNombre(), 16), p.x, p.y + DIAMETRO / 2.0 + 11);
    }

    private void dibujarCentrado(Graphics2D g2, String texto, double x, double y) {
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(texto, (float) (x - fm.stringWidth(texto) / 2.0),
                (float) (y + (fm.getAscent() - fm.getDescent()) / 2.0));
    }

    private String recortar(String texto, int maximo) {
        return texto.length() <= maximo ? texto : texto.substring(0, maximo - 1) + "…";
    }
}
