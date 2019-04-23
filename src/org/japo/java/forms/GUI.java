/*
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
public final class GUI extends JFrame {

    // Referencias
    private final Properties prp;

    // Componentes
    private JLabel lblPregunta;
    private JLabel lblRespuesta;
    private JRadioButton rbtYes;
    private JRadioButton rbtNot;
    private JRadioButton rbtMay;
    private ButtonGroup bg;
    private TitledBorder brdPregunta;
    private TitledBorder brdOpciones;
    private TitledBorder brdRespuesta;
    private JPanel pnlPregunta;
    private JPanel pnlOpciones;
    private JPanel pnlRespuesta;
    private JPanel pnlPpal;

    // Fuentes
    private Font fntControl;
    private Font fntBorder;

    // Tamaño Imagen Salida
    private int ancImgOut;
    private int altImgOut;

    // Constructor
    public GUI(Properties prp) {
        // Conectar Referencia
        this.prp = prp;

        // Inicialización Anterior
        initBefore();

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción - GUI
    private void initComponents() {
        // Etiqueta Pregunta
        lblPregunta = new JLabel(prp.getProperty("question"));
        lblPregunta.setHorizontalAlignment(JLabel.CENTER);

        // Opción SI
        rbtYes = new JRadioButton("Seguramente");
        rbtYes.setPreferredSize(new Dimension(200, 30));
        rbtYes.setFocusable(false);

        // Opción NO
        rbtNot = new JRadioButton("Dificilmente");
        rbtNot.setPreferredSize(new Dimension(200, 30));
        rbtNot.setFocusable(false);

        // Opción NS/NC
        rbtMay = new JRadioButton("No sabría decir");
        rbtMay.setPreferredSize(new Dimension(200, 30));
        rbtMay.setSelected(true);
        rbtMay.requestFocus();
        rbtMay.setFocusable(false);

        // Coordinador de botones
        bg = new ButtonGroup();
        bg.add(rbtYes);
        bg.add(rbtNot);
        bg.add(rbtMay);

        // Imagen Respuesta
        ancImgOut = Integer.parseInt(prp.getProperty("img_answer_width"));
        altImgOut = Integer.parseInt(prp.getProperty("img_answer_height"));
        Image imgOut = UtilesSwing.importarImagenRecurso(
                prp.getProperty("img_may_resource"), ancImgOut, altImgOut);

        // Etiqueta Imagen Respuesta
        lblRespuesta = new JLabel(new ImageIcon(imgOut));

        // Borde Panel Pregunta
        brdPregunta = new TitledBorder("Pregunta");

        // Panel Pregunta
        pnlPregunta = new JPanel();
        pnlPregunta.setPreferredSize(new Dimension(0, 100));
        pnlPregunta.setBorder(brdPregunta);
        pnlPregunta.setLayout(new GridBagLayout());
        pnlPregunta.add(lblPregunta);

        // Borde Panel Opciones
        brdOpciones = new TitledBorder("Opciones");

        // Panel Opciones
        pnlOpciones = new JPanel();
        pnlOpciones.setPreferredSize(new Dimension(190, 0));
        pnlOpciones.setBorder(brdOpciones);
        pnlOpciones.setLayout(new BoxLayout(pnlOpciones, BoxLayout.Y_AXIS));
        pnlOpciones.add(Box.createHorizontalStrut(50));
        pnlOpciones.add(rbtYes);
        pnlOpciones.add(rbtNot);
        pnlOpciones.add(rbtMay);
        pnlOpciones.add(Box.createHorizontalStrut(50));

        // Borde Panel Respuesta
        brdRespuesta = new TitledBorder("Respuesta");

        // Panel Respuesta
        pnlRespuesta = new JPanel(new GridBagLayout());
        pnlRespuesta.setPreferredSize(new Dimension(170, 0));
        pnlRespuesta.setBorder(brdRespuesta);
        pnlRespuesta.add(lblRespuesta);

        // Panel Principal
        pnlPpal.setLayout(new BorderLayout(5, 5));
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPpal.add(pnlPregunta, BorderLayout.NORTH);
        pnlPpal.add(pnlRespuesta, BorderLayout.EAST);
        pnlPpal.add(pnlOpciones, BorderLayout.CENTER);

        // Ventana Principal
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty("look_and_feel_profile"));

        // Fuentes
        fntControl = UtilesSwing.generarFuenteRecurso(prp.getProperty("font_control_resource"));
        fntBorder = UtilesSwing.generarFuenteRecurso(prp.getProperty("font_border_resource"));

        // Panel Principal
        pnlPpal = new JPanel();

        // Ventana Principal
        setContentPane(pnlPpal);
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty("img_favicon_resource"));

        // Tipografías
        lblPregunta.setFont(fntControl.deriveFont(Font.PLAIN, 20f));
        rbtYes.setFont(fntControl.deriveFont(Font.PLAIN, 20f));
        rbtNot.setFont(fntControl.deriveFont(Font.PLAIN, 20f));
        rbtMay.setFont(fntControl.deriveFont(Font.PLAIN, 20f));
        brdPregunta.setTitleFont(fntBorder.deriveFont(Font.BOLD, 18f));
        brdOpciones.setTitleFont(fntBorder.deriveFont(Font.BOLD, 18f));
        brdRespuesta.setTitleFont(fntBorder.deriveFont(Font.BOLD, 18f));

        // Ventana Principal
        setTitle(prp.getProperty("form_title"));
        int width = Integer.parseInt(prp.getProperty("form_width"));
        int height = Integer.parseInt(prp.getProperty("form_height"));
        setSize(width, height);
        setLocationRelativeTo(null);

        // Registro de los Gestores de Eventos
        rbtYes.addActionListener(new AEM(this));
        rbtNot.addActionListener(new AEM(this));
        rbtMay.addActionListener(new AEM(this));
    }

    public final void procesarEncuesta(ActionEvent ae) {
        // Recurso Imagen
        String recurso;

        // Selección Recurso Imagen
        if (ae.getSource().equals(rbtYes)) {
            recurso = prp.getProperty("img_yes_resource");
        } else if (ae.getSource().equals(rbtNot)) {
            recurso = prp.getProperty("img_not_resource");
        } else {
            recurso = prp.getProperty("img_may_resource");
        }

        // Image ( Original ) >> Image ( Reescalada )
        Image imgAct = UtilesSwing.importarImagenRecurso(recurso, ancImgOut, altImgOut);

        // Image >> JLabel
        lblRespuesta.setIcon(new ImageIcon(imgAct));
    }
}
