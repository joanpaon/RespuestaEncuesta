/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import org.japo.java.events.AEM;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Tamaño de la ventana
    public static final int VENTANA_ANC = 400;
    public static final int VENTANA_ALT = 250;

    // Tamaño de la imagen
    public static final int IMAGEN_ANC = 100;
    public static final int IMAGEN_ALT = 100;

    // Pregunta de la encuesta
    private static final String PREGUNTA = "¿Cree Ud. que éste va a ser un buen año?";

    // Nombres de los archivos gráficos
    private static final String IMG_SI = "si.jpg";
    private static final String IMG_NO = "no.jpg";
    private static final String IMG_TV = "tv.jpg";

    // Ruta a los archivos gráficos
    private static final String RUTA_IMG = "images";

    // Referencias a los componentes
    private JLabel lblPregunta;
    private JRadioButton rbtSI;
    private JRadioButton rbtNO;
    private JRadioButton rbtTV;
    private JLabel lblImagen;

    public GUI() {
        // Inicialización PREVIA
        beforeInit();

        // Creación del interfaz
        initComponents();

        // Inicialización POSTERIOR
        afterInit();
    }

    // Construcción del IGU
    private void initComponents() {
        // Bordes
        Border brdPnlPrincipal = new EmptyBorder(10, 10, 10, 10);
        Border brdPnlPregunta = new TitledBorder("Pregunta");
        Border brdPnlOpciones = new TitledBorder("Opciones");
        Border brdPnlImagen = new TitledBorder("Imagen");

        // Tamaños
        Dimension dimBotonRadio = new Dimension(150, 30);
        Dimension dimPnlPregunta = new Dimension(0, 50);
        Dimension dimPnlOpciones = new Dimension(190, 0);
        Dimension dimPnlImagen = new Dimension(170, 0);

        // Fuente
        Font f = new Font("Calibri", Font.BOLD, 20);

        // Gestor de Eventos de Accion
        AEM aem = new AEM(this);

        // Etiqueta Pregunta
        lblPregunta = new JLabel(PREGUNTA);
        lblPregunta.setFont(f);
        lblPregunta.setHorizontalAlignment(JLabel.CENTER);

        // Opción SI
        rbtSI = new JRadioButton("Afirmativo");
        rbtSI.setFont(f);
        rbtSI.setPreferredSize(dimBotonRadio);
        rbtSI.addActionListener(aem);

        // Opción NO
        rbtNO = new JRadioButton("Negativo");
        rbtNO.setFont(f);
        rbtNO.setPreferredSize(dimBotonRadio);
        rbtNO.addActionListener(aem);

        // Opción TV
        rbtTV = new JRadioButton("N/S - N/C");
        rbtTV.setFont(f);
        rbtTV.setPreferredSize(dimBotonRadio);
        rbtTV.addActionListener(aem);
        rbtTV.setSelected(true);

        // Coordinador de botones
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtSI);
        bg.add(rbtNO);
        bg.add(rbtTV);

        // Etiqueta con imagen
        lblImagen = new JLabel();

        // Panel Pregunta
        JPanel pnlPregunta = new JPanel();
        pnlPregunta.setPreferredSize(dimPnlPregunta);
        pnlPregunta.setBorder(brdPnlPregunta);
        pnlPregunta.add(lblPregunta);

        // Panel Opciones
        JPanel pnlOpciones = new JPanel();
        pnlOpciones.setPreferredSize(dimPnlOpciones);
        pnlOpciones.setBorder(brdPnlOpciones);
        pnlOpciones.add(rbtSI);
        pnlOpciones.add(rbtNO);
        pnlOpciones.add(rbtTV);

        // Panel Imagen
        JPanel pnlImagen = new JPanel();
        pnlImagen.setPreferredSize(dimPnlImagen);
        pnlImagen.setBorder(brdPnlImagen);
        pnlImagen.add(lblImagen);

        // Panel Principal
        JPanel pnlPpal = new JPanel();
        pnlPpal.setLayout(new BorderLayout(5, 5));
        pnlPpal.setBorder(brdPnlPrincipal);
        pnlPpal.add(pnlPregunta, BorderLayout.NORTH);
        pnlPpal.add(pnlOpciones, BorderLayout.WEST);
        pnlPpal.add(pnlImagen, BorderLayout.EAST);

        // Ventana principal
        setTitle("Respuesta Encuesta");
        setContentPane(pnlPpal);
        setResizable(false);
        setSize(VENTANA_ANC, VENTANA_ALT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización antes del IGU
    private void beforeInit() {

    }

    // Inicialización después del IGU
    private void afterInit() {
        rbtTV.doClick();
    }

    public void procesarEncuesta(ActionEvent e) {
        // Ruta Imagen
        String rutaImagen;
        
        // Selección Imagen
        if (e.getSource().equals(rbtSI)) {
            rutaImagen = RUTA_IMG + "/" + IMG_SI;
        } else if (e.getSource().equals(rbtNO)) {
            rutaImagen = RUTA_IMG + "/" + IMG_NO;
        } else {
            rutaImagen = RUTA_IMG + "/" + IMG_TV;
        }
        
        // Archivo Gráfico > ImageIcon
        ImageIcon ii = new ImageIcon(rutaImagen);
        
        // ImageIcon > Image (Inicial)
        Image imgIni = ii.getImage();

        // Image (Inicial) + Rescalado > Image (Final)
        Image imgFin = imgIni.getScaledInstance(
            IMAGEN_ANC, IMAGEN_ALT, Image.SCALE_FAST);
        
        // Image (Final) > Icon
        Icon i = new ImageIcon(imgFin);
        
        // Icon > Etiqueta Imagen
        lblImagen.setIcon(i);
    }

}
