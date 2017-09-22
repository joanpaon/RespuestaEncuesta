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
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import org.japo.java.events.AEM;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL = "look_and_feel";
    public static final String PRP_FAVICON = "favicon";
    public static final String PRP_WIDTH = "image.width";
    public static final String PRP_HEIGHT = "image.height";
    public static final String PRP_QUESTION = "question";
    public static final String PRP_YES = "image.yes";
    public static final String PRP_NOT = "image.not";
    public static final String PRP_MAY = "image.maybe";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL = UtilesSwing.LNF_NIMBUS;
    public static final String DEF_FAVICON = "img/favicon.png";
    public static final String DEF_WIDTH = "100";
    public static final String DEF_HEIGHT = "100";
    public static final String DEF_QUESTION = "¿Cree Ud. que éste va a ser un buen año?";
    public static final String DEF_YES = "img/si.jpg";
    public static final String DEF_NOT = "img/no.jpg";
    public static final String DEF_MAY = "img/tv.jpg";

    // Referencias
    private Properties prp;
    private JLabel lblPregunta;
    private JRadioButton rbtSI;
    private JRadioButton rbtNO;
    private JRadioButton rbtTV;
    private JLabel lblImagen;

    // Constructor
    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Etiqueta Pregunta
        lblPregunta = new JLabel(prp.getProperty(PRP_QUESTION, DEF_QUESTION));
        lblPregunta.setFont(new Font("Calibri", Font.BOLD, 30));
        lblPregunta.setHorizontalAlignment(JLabel.CENTER);

        // Opción SI
        rbtSI = new JRadioButton("Afirmativo");
        rbtSI.setFont(new Font("Calibri", Font.BOLD, 20));
        rbtSI.setPreferredSize(new Dimension(150, 30));
        rbtSI.addActionListener(new AEM(this));

        // Opción NO
        rbtNO = new JRadioButton("Negativo");
        rbtNO.setFont(new Font("Calibri", Font.BOLD, 20));
        rbtNO.setPreferredSize(new Dimension(150, 30));
        rbtNO.addActionListener(new AEM(this));

        // Opción TV
        rbtTV = new JRadioButton("N/S - N/C");
        rbtTV.setFont(new Font("Calibri", Font.BOLD, 20));
        rbtTV.setPreferredSize(new Dimension(150, 30));
        rbtTV.addActionListener(new AEM(this));

        // Coordinador de botones
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtSI);
        bg.add(rbtNO);
        bg.add(rbtTV);

        // Etiqueta con imagen
        lblImagen = new JLabel();

        // Panel Pregunta
        JPanel pnlPregunta = new JPanel();
        pnlPregunta.setPreferredSize(new Dimension(0, 100));
        pnlPregunta.setBorder(new TitledBorder("Pregunta"));
        pnlPregunta.setLayout(new GridBagLayout());
        pnlPregunta.add(lblPregunta);

        // Panel Opciones
        JPanel pnlOpciones = new JPanel();
        pnlOpciones.setPreferredSize(new Dimension(190, 0));
        pnlOpciones.setBorder(new TitledBorder("Opciones"));
        pnlOpciones.add(rbtSI);
        pnlOpciones.add(rbtNO);
        pnlOpciones.add(rbtTV);

        // Panel Imagen
        JPanel pnlImagen = new JPanel();
        pnlImagen.setPreferredSize(new Dimension(170, 0));
        pnlImagen.setBorder(new TitledBorder("Imagen"));
        pnlImagen.setLayout(new GridBagLayout());
        pnlImagen.add(lblImagen);

        // Panel Principal
        JPanel pnlPpal = new JPanel();
        pnlPpal.setLayout(new BorderLayout(5, 5));
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPpal.add(pnlPregunta, BorderLayout.NORTH);
        pnlPpal.add(pnlOpciones, BorderLayout.CENTER);
        pnlPpal.add(pnlImagen, BorderLayout.EAST);

        // Ventana principal
        setContentPane(pnlPpal);
        setTitle("Swing Manual #09");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnF(prp.getProperty(PRP_LOOK_AND_FEEL, DEF_LOOK_AND_FEEL));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_FAVICON, DEF_FAVICON));

        // Selección Inicial
        rbtTV.doClick();
    }

    public void procesarEncuesta(ActionEvent ae) {
        try {
            // Ruta Imagen
            String ruta;

            // Selección Ruta Imagen
            if (ae.getSource().equals(rbtSI)) {
                ruta = prp.getProperty(PRP_YES, DEF_YES);
            } else if (ae.getSource().equals(rbtNO)) {
                ruta = prp.getProperty(PRP_NOT, DEF_NOT);
            } else {
                ruta = prp.getProperty(PRP_MAY, DEF_MAY);
            }

            // Ruta > URL
            URL url = ClassLoader.getSystemResource(ruta);

            // URL > Image (Inicial)
            Image imgIni = new ImageIcon(url).getImage();

            // Tamaño Reescalado
            int ancho = Integer.parseInt(prp.getProperty(PRP_WIDTH, DEF_WIDTH));
            int alto = Integer.parseInt(prp.getProperty(PRP_HEIGHT, DEF_HEIGHT));

            // Image (Inicial) + Rescalado > Image (Final)
            Image imgFin = imgIni.getScaledInstance(ancho, alto, Image.SCALE_FAST);

            // Image (Final) > Icon
            Icon i = new ImageIcon(imgFin);

            // Icon > Etiqueta Imagen
            lblImagen.setIcon(i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
