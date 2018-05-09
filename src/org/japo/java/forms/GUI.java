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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_BACKGROUND_RESOURCE = "background_resource";
    public static final String PRP_FONT1_RESOURCE = "font1_resource";
    public static final String PRP_FONT2_RESOURCE = "font2_resource";
    public static final String PRP_IMAGE_WIDTH = "image_width";
    public static final String PRP_IMAGE_HEIGHT = "image_height";
    public static final String PRP_QUESTION = "question";
    public static final String PRP_IMAGE_YES_RESOURCE = "image_yes_resource";
    public static final String PRP_IMAGE_NOT_RESOURCE = "image_not_resource";
    public static final String PRP_IMAGE_MAY_RESOURCE = "image_maybe_resource";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;
    public static final String DEF_FAVICON_RESOURCE = "images/favicon.png";
    public static final String DEF_BACKGROUND_RESOURCE = "images/background.png";
    public static final String DEF_FONT1_RESOURCE = "fonts/default_font1.ttf";
    public static final String DEF_FONT2_RESOURCE = "fonts/default_font2.ttf";
    public static final String DEF_IMAGE_WIDTH = "100";
    public static final String DEF_IMAGE_HEIGHT = "100";
    public static final String DEF_QUESTION = "¿Cree Ud. que éste va a ser un buen año?";
    public static final String DEF_IMAGE_YES_RESOURCE = "images/yes.jpg";
    public static final String DEF_IMAGE_NOT_RESOURCE = "images/not.jpg";
    public static final String DEF_IMAGE_MAY_RESOURCE = "images/may.jpg";

    // Referencias
    private Properties prp;
    private JLabel lblPregunta;
    private JRadioButton rbtYes;
    private JRadioButton rbtNot;
    private JRadioButton rbtMay;
    private JLabel lblRespuesta;

    // Tamaño Imagen Salida
    private int ancImgOut;
    private int altImgOut;

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
        lblPregunta.setFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT1_RESOURCE, DEF_FONT1_RESOURCE)).
                deriveFont(Font.PLAIN, 24f));
        lblPregunta.setHorizontalAlignment(JLabel.CENTER);

        // Opción SI
        rbtYes = new JRadioButton("Seguramente");
        rbtYes.setFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT1_RESOURCE, DEF_FONT1_RESOURCE)).
                deriveFont(Font.PLAIN, 20f));
        rbtYes.setPreferredSize(new Dimension(200, 30));
        rbtYes.addActionListener(new AEM(this));

        // Opción NO
        rbtNot = new JRadioButton("Dificilmente");
        rbtNot.setFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT1_RESOURCE, DEF_FONT1_RESOURCE)).
                deriveFont(Font.PLAIN, 20f));
        rbtNot.setPreferredSize(new Dimension(200, 30));
        rbtNot.addActionListener(new AEM(this));

        // Opción NS/NC
        rbtMay = new JRadioButton("No sabría decir");
        rbtMay.setFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT1_RESOURCE, DEF_FONT1_RESOURCE)).
                deriveFont(Font.PLAIN, 20f));
        rbtMay.setPreferredSize(new Dimension(200, 30));
        rbtMay.addActionListener(new AEM(this));

        // Coordinador de botones
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtYes);
        bg.add(rbtNot);
        bg.add(rbtMay);

        // Tamaño Imagen Respuesta
        ancImgOut = Integer.parseInt(prp.getProperty(PRP_IMAGE_WIDTH, DEF_IMAGE_WIDTH));
        altImgOut = Integer.parseInt(prp.getProperty(PRP_IMAGE_HEIGHT, DEF_IMAGE_HEIGHT));

        // Imagen Respuesta
        String prpImgOut = prp.getProperty(PRP_IMAGE_MAY_RESOURCE, DEF_IMAGE_MAY_RESOURCE);
        URL urlImgOut = ClassLoader.getSystemResource(prpImgOut);
        Image imgOutIni = new ImageIcon(urlImgOut).getImage();
        Image imgOutFin = imgOutIni.getScaledInstance(ancImgOut, altImgOut, Image.SCALE_FAST);

        // Etiqueta Imagen Respuesta
        lblRespuesta = new JLabel(new ImageIcon(imgOutFin));

        // Borde Panel Pregunta
        TitledBorder brdPregunta = new TitledBorder("Pregunta");
        brdPregunta.setTitleFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT2_RESOURCE, DEF_FONT2_RESOURCE)).
                deriveFont(Font.BOLD, 18f));

        // Panel Pregunta
        JPanel pnlPregunta = new JPanel();
        pnlPregunta.setPreferredSize(new Dimension(0, 100));
        pnlPregunta.setBorder(brdPregunta);
        pnlPregunta.setLayout(new GridBagLayout());
        pnlPregunta.add(lblPregunta);

        // Borde Panel Opciones
        TitledBorder brdOpciones = new TitledBorder("Opciones");
        brdOpciones.setTitleFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT2_RESOURCE, DEF_FONT2_RESOURCE)).
                deriveFont(Font.BOLD, 18f));

        // Panel Opciones
        JPanel pnlOpciones = new JPanel();
        pnlOpciones.setPreferredSize(new Dimension(190, 0));
        pnlOpciones.setBorder(brdOpciones);
        pnlOpciones.setLayout(new BoxLayout(pnlOpciones, BoxLayout.Y_AXIS));
        pnlOpciones.add(Box.createHorizontalStrut(50));
        pnlOpciones.add(rbtYes);
        pnlOpciones.add(rbtNot);
        pnlOpciones.add(rbtMay);
        pnlOpciones.add(Box.createHorizontalStrut(50));

        // Borde Panel Respuesta
        TitledBorder brdRespuesta = new TitledBorder("Respuesta");
        brdRespuesta.setTitleFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT2_RESOURCE, DEF_FONT2_RESOURCE)).
                deriveFont(Font.BOLD, 18f));

        // Panel Respuesta
        JPanel pnlRespuesta = new JPanel(new GridBagLayout());
        pnlRespuesta.setPreferredSize(new Dimension(170, 0));
        pnlRespuesta.setBorder(brdRespuesta);
        pnlRespuesta.add(lblRespuesta);

        // Panel Principal
        JPanel pnlPpal = new JPanel(new BorderLayout(5, 5));
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPpal.add(pnlPregunta, BorderLayout.NORTH);
        pnlPpal.add(pnlRespuesta, BorderLayout.EAST);
        pnlPpal.add(pnlOpciones, BorderLayout.CENTER);

        // Ventana principal
        setContentPane(pnlPpal);
        setTitle("Swing Manual #09");
        setSize(500, 300);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));

        // Selección Inicial
        rbtMay.doClick();
    }

    public void procesarEncuesta(ActionEvent ae) {
        // Recurso Imagen
        String recurso;

        // Selección Recurso Imagen
        if (ae.getSource().equals(rbtYes)) {
            recurso = prp.getProperty(PRP_IMAGE_YES_RESOURCE, DEF_IMAGE_YES_RESOURCE);
        } else if (ae.getSource().equals(rbtNot)) {
            recurso = prp.getProperty(PRP_IMAGE_NOT_RESOURCE, DEF_IMAGE_NOT_RESOURCE);
        } else {
            recurso = prp.getProperty(PRP_IMAGE_MAY_RESOURCE, DEF_IMAGE_MAY_RESOURCE);
        }

        // Recurso >> URL
        URL urlRecurso = ClassLoader.getSystemResource(recurso);

        // URL >> Image
        Image imgRecurso = new ImageIcon(urlRecurso).getImage();

        // Image >> JLabel
        UtilesSwing.escalarImagenEtiqueta(lblRespuesta, imgRecurso, ancImgOut, altImgOut);
    }
}
