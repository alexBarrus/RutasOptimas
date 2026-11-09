package FrontEnd;

import BackEnd.AlgoritmoCamino;
import BackEnd.Arista;
import BackEnd.BellmanFord;
import BackEnd.Dijkstra;
import BackEnd.Grafo;
import BackEnd.GrafoException;
import BackEnd.ResultadoRuta;
import BackEnd.Vertice;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * Ventana principal. El diseño se edita en la pestaña Design de NetBeans (GUIGrafo.form).
 */
public class GUIGrafo extends javax.swing.JFrame {

    private static final int PAUSA_TRAZA_MS = 40;

    private final PanelGrafo panelGrafo = new PanelGrafo();
    private Grafo grafo;

    public GUIGrafo() {
        initComponents();
        panelLienzo.add(panelGrafo, java.awt.BorderLayout.CENTER);
        setLocationRelativeTo(null);
        actualizarEstado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelControl = new javax.swing.JPanel();
        lblTipoGrafo = new javax.swing.JLabel();
        cmbTipoGrafo = new javax.swing.JComboBox<>();
        btnCrearGrafo = new javax.swing.JButton();
        btnDestruirGrafo = new javax.swing.JButton();
        lblEstadoGrafo = new javax.swing.JLabel();
        panelVertice = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        lblEstadoRegion = new javax.swing.JLabel();
        txtEstadoRegion = new javax.swing.JTextField();
        lblCapacidad = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        btnAgregarVertice = new javax.swing.JButton();
        btnBuscarVertice = new javax.swing.JButton();
        btnEliminarVertice = new javax.swing.JButton();
        panelArista = new javax.swing.JPanel();
        lblOrigen = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        lblDestino = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        btnAgregarArista = new javax.swing.JButton();
        btnEliminarArista = new javax.swing.JButton();
        panelOperaciones = new javax.swing.JPanel();
        lblNodoInicial = new javax.swing.JLabel();
        txtNodoInicial = new javax.swing.JTextField();
        btnBFS = new javax.swing.JButton();
        btnDFS = new javax.swing.JButton();
        btnMatriz = new javax.swing.JButton();
        btnConectividad = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        panelOptimizacion = new javax.swing.JPanel();
        lblRutaOrigen = new javax.swing.JLabel();
        cmbRutaOrigen = new javax.swing.JComboBox<>();
        lblRutaDestino = new javax.swing.JLabel();
        cmbRutaDestino = new javax.swing.JComboBox<>();
        lblAlgoritmo = new javax.swing.JLabel();
        cmbAlgoritmo = new javax.swing.JComboBox<>();
        btnCalcularRuta = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();
        btnLimpiarTraza = new javax.swing.JButton();
        panelLienzo = new javax.swing.JPanel();
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        scrollSalida = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        scrollTraza = new javax.swing.JScrollPane();
        txtTraza = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Optimización de rutas en grafos");

        panelControl.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafo"));

        lblTipoGrafo.setText("Tipo:");
        cmbTipoGrafo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No dirigido", "Dirigido" }));
        btnCrearGrafo.setText("Crear grafo");
        btnCrearGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearGrafo(evt);
            }
        });
        btnDestruirGrafo.setText("Destruir grafo");
        btnDestruirGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destruirGrafo(evt);
            }
        });
        lblEstadoGrafo.setText("Estado: grafo sin crear");

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelControlLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelControlLayout.createSequentialGroup()
                            .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTipoGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbTipoGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelControlLayout.createSequentialGroup()
                            .addComponent(btnCrearGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDestruirGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addComponent(lblEstadoGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelControlLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipoGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbTipoGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCrearGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDestruirGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblEstadoGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelVertice.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicación (vértice)"));

        lblId.setText("Identificador:");
        lblNombre.setText("Nombre:");
        lblTipo.setText("Tipo:");
        lblEstadoRegion.setText("Estado / zona:");
        lblCapacidad.setText("Capacidad:");
        btnAgregarVertice.setText("Agregar");
        btnAgregarVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarVertice(evt);
            }
        });
        btnBuscarVertice.setText("Buscar");
        btnBuscarVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarVertice(evt);
            }
        });
        btnEliminarVertice.setText("Eliminar");
        btnEliminarVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarVertice(evt);
            }
        });

        javax.swing.GroupLayout panelVerticeLayout = new javax.swing.GroupLayout(panelVertice);
        panelVertice.setLayout(panelVerticeLayout);
        panelVerticeLayout.setHorizontalGroup(
            panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVerticeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelVerticeLayout.createSequentialGroup()
                            .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEstadoRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEstadoRegion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelVerticeLayout.createSequentialGroup()
                            .addComponent(btnAgregarVertice, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarVertice, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEliminarVertice, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        panelVerticeLayout.setVerticalGroup(
            panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVerticeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstadoRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEstadoRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelVerticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarVertice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarVertice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarVertice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelArista.setBorder(javax.swing.BorderFactory.createTitledBorder("Ruta (arista)"));

        lblOrigen.setText("Origen:");
        lblDestino.setText("Destino:");
        lblPeso.setText("Peso / distancia:");
        btnAgregarArista.setText("Agregar ruta");
        btnAgregarArista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarArista(evt);
            }
        });
        btnEliminarArista.setText("Eliminar ruta");
        btnEliminarArista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarArista(evt);
            }
        });

        javax.swing.GroupLayout panelAristaLayout = new javax.swing.GroupLayout(panelArista);
        panelArista.setLayout(panelAristaLayout);
        panelAristaLayout.setHorizontalGroup(
            panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAristaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAristaLayout.createSequentialGroup()
                            .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelAristaLayout.createSequentialGroup()
                            .addComponent(btnAgregarArista, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEliminarArista, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        panelAristaLayout.setVerticalGroup(
            panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAristaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarArista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarArista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOperaciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Recorridos y análisis"));

        lblNodoInicial.setText("Nodo inicial:");
        btnBFS.setText("Recorrido BFS");
        btnBFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarBFS(evt);
            }
        });
        btnDFS.setText("Recorrido DFS");
        btnDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarDFS(evt);
            }
        });
        btnMatriz.setText("Matriz");
        btnMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarMatriz(evt);
            }
        });
        btnConectividad.setText("Conectividad");
        btnConectividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluarConectividad(evt);
            }
        });
        btnVisualizar.setText("Ver estructura del grafo");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarGrafo(evt);
            }
        });

        javax.swing.GroupLayout panelOperacionesLayout = new javax.swing.GroupLayout(panelOperaciones);
        panelOperaciones.setLayout(panelOperacionesLayout);
        panelOperacionesLayout.setHorizontalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOperacionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOperacionesLayout.createSequentialGroup()
                            .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNodoInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelOperacionesLayout.createSequentialGroup()
                            .addComponent(btnBFS, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDFS, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGroup(panelOperacionesLayout.createSequentialGroup()
                            .addComponent(btnMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConectividad, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGroup(panelOperacionesLayout.createSequentialGroup()
                            .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        panelOperacionesLayout.setVerticalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOperacionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConectividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOptimizacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Optimización de rutas"));

        lblRutaOrigen.setText("Origen:");
        cmbRutaOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        lblRutaDestino.setText("Destino:");
        cmbRutaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        lblAlgoritmo.setText("Algoritmo:");
        cmbAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dijkstra", "Bellman-Ford" }));
        btnCalcularRuta.setText("Calcular ruta");
        btnCalcularRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRutaOptima(evt);
            }
        });
        btnComparar.setText("Comparar");
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compararDesempeno(evt);
            }
        });
        btnLimpiarTraza.setText("Limpiar traza");
        btnLimpiarTraza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarRegistro(evt);
            }
        });

        javax.swing.GroupLayout panelOptimizacionLayout = new javax.swing.GroupLayout(panelOptimizacion);
        panelOptimizacion.setLayout(panelOptimizacionLayout);
        panelOptimizacionLayout.setHorizontalGroup(
            panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOptimizacionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOptimizacionLayout.createSequentialGroup()
                            .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblRutaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblRutaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbRutaOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbRutaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelOptimizacionLayout.createSequentialGroup()
                            .addComponent(btnCalcularRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnComparar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLimpiarTraza, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        panelOptimizacionLayout.setVerticalGroup(
            panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOptimizacionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRutaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbRutaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRutaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbRutaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelOptimizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCalcularRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnComparar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarTraza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLienzo.setBorder(javax.swing.BorderFactory.createTitledBorder("Representación gráfica y camino óptimo"));
        panelLienzo.setLayout(new java.awt.BorderLayout());

        scrollResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado de la ruta"));

        txtResultado.setEditable(false);
        txtResultado.setColumns(20);
        txtResultado.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setRows(3);
        scrollResultado.setViewportView(txtResultado);

        scrollSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensajes de operaciones"));

        txtSalida.setEditable(false);
        txtSalida.setColumns(20);
        txtSalida.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtSalida.setRows(3);
        scrollSalida.setViewportView(txtSalida);

        scrollTraza.setBorder(javax.swing.BorderFactory.createTitledBorder("Traza paso a paso"));
        scrollTraza.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollTraza.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtTraza.setEditable(false);
        txtTraza.setColumns(20);
        txtTraza.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtTraza.setRows(3);
        scrollTraza.setViewportView(txtTraza);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelVertice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelArista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addComponent(scrollResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addComponent(scrollSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelOptimizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                        .addComponent(scrollTraza, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelVertice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelArista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelOptimizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollTraza, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearGrafo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearGrafo
        if (grafo != null && !grafo.estaVacio()
                && !confirmar("El grafo actual tiene datos y se perderán. ¿Crear uno nuevo?")) {
            return;
        }
        boolean dirigido = cmbTipoGrafo.getSelectedIndex() == 1;
        grafo = new Grafo(dirigido);
        panelGrafo.setGrafo(grafo);
        invalidarRuta();
        txtTraza.setText("");
        limpiarCampos();
        actualizarEstado();
        exito("Grafo " + (dirigido ? "dirigido" : "no dirigido") + " creado y listo para usarse.");
    }//GEN-LAST:event_crearGrafo

    private void agregarVertice(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarVertice
        modificar(() -> {
            String nombre = obligatorio(txtNombre, "nombre");
            String tipo = obligatorio(txtTipo, "tipo");
            String estado = obligatorio(txtEstadoRegion, "estado");
            Vertice vertice = new Vertice(txtId.getText(), nombre, tipo, estado,
                    leerNumero(txtCapacidad, "capacidad"));
            grafo.agregarVertice(vertice);
            exito("Ubicación agregada -> " + vertice);
            limpiarCamposVertice();
        });
    }//GEN-LAST:event_agregarVertice

    private void buscarVertice(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarVertice
        ejecutar(() -> exito("Ubicación encontrada -> " + grafo.buscarVertice(txtId.getText())));
    }//GEN-LAST:event_buscarVertice

    private void eliminarVertice(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarVertice
        modificar(() -> {
            String id = txtId.getText().trim();
            int rutas = grafo.eliminarVertice(id);
            exito("Ubicación '" + id + "' eliminada junto con " + rutas + " ruta(s) conectada(s).");
            limpiarCamposVertice();
        });
    }//GEN-LAST:event_eliminarVertice

    private void agregarArista(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarArista
        modificar(() -> {
            double peso = leerNumero(txtPeso, "peso");
            grafo.agregarArista(txtOrigen.getText(), txtDestino.getText(), peso);
            exito("Ruta agregada: " + txtOrigen.getText().trim() + (grafo.esDirigido() ? " -> " : " <-> ")
                    + txtDestino.getText().trim() + " (peso " + Grafo.formatear(peso) + ").");
            limpiarCamposArista();
        });
    }//GEN-LAST:event_agregarArista

    private void eliminarArista(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarArista
        modificar(() -> {
            grafo.eliminarArista(txtOrigen.getText(), txtDestino.getText());
            exito("Ruta eliminada: " + txtOrigen.getText().trim() + " - " + txtDestino.getText().trim() + ".");
            limpiarCamposArista();
        });
    }//GEN-LAST:event_eliminarArista

    private void mostrarBFS(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarBFS
        ejecutar(() -> mostrarRecorrido("BFS", grafo.recorridoBFS(txtNodoInicial.getText())));
    }//GEN-LAST:event_mostrarBFS

    private void mostrarDFS(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarDFS
        ejecutar(() -> mostrarRecorrido("DFS", grafo.recorridoDFS(txtNodoInicial.getText())));
    }//GEN-LAST:event_mostrarDFS

    private void mostrarMatriz(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarMatriz
        ejecutar(() -> escribir("Matriz de adyacencia (0 = sin ruta):\n" + grafo.matrizComoTexto()));
    }//GEN-LAST:event_mostrarMatriz

    private void evaluarConectividad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evaluarConectividad
        ejecutar(() -> {
            Grafo.Conectividad resultado = grafo.evaluarConectividad();
            String nota = !grafo.esDirigido() && resultado == Grafo.Conectividad.FUERTEMENTE_CONECTADO
                    ? " (en un grafo no dirigido equivale a conexo)" : "";
            exito("Conectividad del grafo: " + resultado + nota + ".");
        });
    }//GEN-LAST:event_evaluarConectividad

    private void visualizarGrafo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizarGrafo
        ejecutar(() -> escribir("Estructura del grafo (lista de adyacencia):\n" + grafo.estructuraComoTexto()));
    }//GEN-LAST:event_visualizarGrafo

    private void destruirGrafo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destruirGrafo
        if (grafo == null) {
            error("Primero debe crear el grafo.");
            return;
        }
        if (!grafo.estaVacio() && !confirmar("¿Eliminar todas las ubicaciones y rutas?")) {
            return;
        }
        grafo.destruir();
        limpiarCampos();
        txtSalida.setText("");
        txtTraza.setText("");
        invalidarRuta();
        panelGrafo.repaint();
        actualizarEstado();
        exito("Grafo destruido: quedó sin ubicaciones ni rutas.");
    }//GEN-LAST:event_destruirGrafo

    private void calcularRutaOptima(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRutaOptima
        iniciarBusqueda(new int[] {cmbAlgoritmo.getSelectedIndex()});
    }//GEN-LAST:event_calcularRutaOptima

    private void compararDesempeno(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compararDesempeno
        iniciarBusqueda(new int[] {0, 1});
    }//GEN-LAST:event_compararDesempeno

    private void limpiarRegistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarRegistro
        txtTraza.setText("");
    }//GEN-LAST:event_limpiarRegistro

    /** Valida la selección y lanza la búsqueda sin bloquear la ventana. */
    private void iniciarBusqueda(int[] algoritmos) {
        txtTraza.setText("");
        invalidarRuta();
        if (grafo == null) {
            txtTraza.setText("[ERROR] Primero debe crear el grafo.\n");
            return;
        }
        String origen = (String) cmbRutaOrigen.getSelectedItem();
        String destino = (String) cmbRutaDestino.getSelectedItem();
        if (origen == null || destino == null) {
            txtTraza.setText("[ERROR] Agregue ubicaciones y seleccione el origen y el destino.\n");
            return;
        }
        habilitarControles(getContentPane(), false);
        new Busqueda(algoritmos, origen, destino).execute();
    }

    private void mostrarResultado(List<ResultadoRuta> resultados) {
        StringBuilder texto = new StringBuilder();
        for (ResultadoRuta resultado : resultados) {
            texto.append(resultado.resumen()).append('\n');
        }
        txtResultado.setText(texto.toString().stripTrailing());
        txtResultado.setCaretPosition(0);
        for (ResultadoRuta resultado : resultados) {
            if (resultado.encontrada()) {
                panelGrafo.setCamino(resultado.ruta());
                return;
            }
        }
    }

    private void habilitarControles(Container contenedor, boolean habilitar) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JButton || componente instanceof JComboBox || componente instanceof JTextField) {
                componente.setEnabled(habilitar);
            } else if (componente instanceof Container interno) {
                habilitarControles(interno, habilitar);
            }
        }
    }

    /** Ejecuta los algoritmos en segundo plano y escribe la traza conforme se produce. */
    private class Busqueda extends SwingWorker<List<ResultadoRuta>, String> {

        private final int[] algoritmos;
        private final String origen;
        private final String destino;

        Busqueda(int[] algoritmos, String origen, String destino) {
            this.algoritmos = algoritmos;
            this.origen = origen;
            this.destino = destino;
        }

        @Override
        protected List<ResultadoRuta> doInBackground() {
            List<ResultadoRuta> resultados = new ArrayList<>();
            for (int indice : algoritmos) {
                resultados.add(crearAlgoritmo(indice).calcular(grafo, origen, destino));
            }
            if (resultados.size() == 2) {
                publish(ResultadoRuta.comparar(resultados.get(0), resultados.get(1)));
            }
            return resultados;
        }

        private AlgoritmoCamino crearAlgoritmo(int indice) {
            return indice == 0 ? new Dijkstra(this::emitir) : new BellmanFord(this::emitir);
        }

        private void emitir(String linea) {
            publish(linea);
            try {
                Thread.sleep(PAUSA_TRAZA_MS);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        protected void process(List<String> lineas) {
            for (String linea : lineas) {
                txtTraza.append(linea + "\n");
            }
            txtTraza.setCaretPosition(txtTraza.getDocument().getLength());
        }

        @Override
        protected void done() {
            try {
                mostrarResultado(get());
            } catch (ExecutionException ex) {
                txtTraza.append("[ERROR] " + ex.getCause().getMessage() + "\n");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            habilitarControles(getContentPane(), true);
        }
    }

    /** Aplica un cambio a la red; el camino resaltado deja de ser válido. */
    private void modificar(Runnable operacion) {
        invalidarRuta();
        ejecutar(operacion);
    }

    private void invalidarRuta() {
        txtResultado.setText("");
        panelGrafo.setCamino(List.of());
    }

    private void recargarNodos(JComboBox<String> combo) {
        Object seleccion = combo.getSelectedItem();
        combo.removeAllItems();
        for (Vertice vertice : grafo.getVertices()) {
            combo.addItem(vertice.getId());
        }
        if (seleccion != null) {
            combo.setSelectedItem(seleccion);
        }
    }

    /** Ejecuta una operación y presenta cualquier error de validación en el área de texto. */
    private void ejecutar(Runnable operacion) {
        if (grafo == null) {
            error("Primero debe crear el grafo.");
            return;
        }
        try {
            operacion.run();
        } catch (GrafoException ex) {
            error(ex.getMessage());
        }
        panelGrafo.repaint();
        actualizarEstado();
    }

    private String obligatorio(JTextField campo, String nombre) {
        if (campo.getText().isBlank()) {
            throw new GrafoException("El campo " + nombre + " es obligatorio.");
        }
        return campo.getText().trim();
    }

    private double leerNumero(JTextField campo, String nombre) {
        String texto = obligatorio(campo, nombre);
        if (!texto.matches("[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
            throw new GrafoException("El campo " + nombre + " debe ser numérico (ejemplo: 12.5).");
        }
        return Double.parseDouble(texto);
    }

    private void mostrarRecorrido(String nombre, List<String> secuencia) {
        String mensaje = "Recorrido " + nombre + " desde " + secuencia.get(0) + ": "
                + String.join(" -> ", secuencia);
        int faltantes = grafo.numeroVertices() - secuencia.size();
        if (faltantes > 0) {
            mensaje += "  (" + faltantes + " ubicación(es) no alcanzable(s) desde este origen)";
        }
        escribir(mensaje);
    }

    private void limpiarCamposVertice() {
        txtId.setText("");
        txtNombre.setText("");
        txtTipo.setText("");
        txtEstadoRegion.setText("");
        txtCapacidad.setText("");
    }

    private void limpiarCamposArista() {
        txtOrigen.setText("");
        txtDestino.setText("");
        txtPeso.setText("");
    }

    private void limpiarCampos() {
        limpiarCamposVertice();
        limpiarCamposArista();
        txtNodoInicial.setText("");
    }

    private void actualizarEstado() {
        if (grafo == null) {
            lblEstadoGrafo.setText("Estado: grafo sin crear");
            return;
        }
        List<Arista> rutas = grafo.getAristas();
        lblEstadoGrafo.setText("Estado: " + (grafo.esDirigido() ? "dirigido" : "no dirigido")
                + " | Ubicaciones: " + grafo.numeroVertices() + " | Rutas: " + rutas.size());
        recargarNodos(cmbRutaOrigen);
        recargarNodos(cmbRutaDestino);
    }

    private void exito(String mensaje) {
        escribir("[OK] " + mensaje);
    }

    private void error(String mensaje) {
        escribir("[ERROR] " + mensaje);
    }

    private void escribir(String mensaje) {
        txtSalida.append(mensaje + "\n\n");
        txtSalida.setCaretPosition(txtSalida.getDocument().getLength());
    }

    protected boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new GUIGrafo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarArista;
    private javax.swing.JButton btnAgregarVertice;
    private javax.swing.JButton btnBFS;
    private javax.swing.JButton btnBuscarVertice;
    private javax.swing.JButton btnCalcularRuta;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnConectividad;
    private javax.swing.JButton btnCrearGrafo;
    private javax.swing.JButton btnDFS;
    private javax.swing.JButton btnDestruirGrafo;
    private javax.swing.JButton btnEliminarArista;
    private javax.swing.JButton btnEliminarVertice;
    private javax.swing.JButton btnLimpiarTraza;
    private javax.swing.JButton btnMatriz;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cmbAlgoritmo;
    private javax.swing.JComboBox<String> cmbRutaDestino;
    private javax.swing.JComboBox<String> cmbRutaOrigen;
    private javax.swing.JComboBox<String> cmbTipoGrafo;
    private javax.swing.JLabel lblAlgoritmo;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblEstadoGrafo;
    private javax.swing.JLabel lblEstadoRegion;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNodoInicial;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblRutaDestino;
    private javax.swing.JLabel lblRutaOrigen;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoGrafo;
    private javax.swing.JPanel panelArista;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelLienzo;
    private javax.swing.JPanel panelOperaciones;
    private javax.swing.JPanel panelOptimizacion;
    private javax.swing.JPanel panelVertice;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JScrollPane scrollSalida;
    private javax.swing.JScrollPane scrollTraza;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtEstadoRegion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNodoInicial;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtSalida;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextArea txtTraza;
    // End of variables declaration//GEN-END:variables
}
