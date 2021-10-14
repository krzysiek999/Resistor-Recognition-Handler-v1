/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author krzys
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    
    ImageIcon resistor4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/res4.png")));
    ImageIcon resistor5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/res5.png")));
    ImageIcon resistor6 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/res6.png")));
    
    
    
    JLabel imagelabel = new JLabel();
    
    DefaultComboBoxModel model1 = new DefaultComboBoxModel();
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    DefaultComboBoxModel model3 = new DefaultComboBoxModel();
    DefaultComboBoxModel model4 = new DefaultComboBoxModel();
    DefaultComboBoxModel model5 = new DefaultComboBoxModel();
    DefaultComboBoxModel model6 = new DefaultComboBoxModel();
    Renderer renderer1 = new Renderer(model1);
    Renderer renderer2 = new Renderer(model2);
    Renderer renderer3 = new Renderer(model3);
    Renderer renderer4 = new Renderer(model4);
    Renderer renderer5 = new Renderer(model5);
    Renderer renderer6 = new Renderer(model6);
    
    StringBuffer resistance = new StringBuffer();
    
    double value1, value2, value3, value4, value5, value6;

    public MainPanel() 
    {
        super();
        initComponents();
        showCombo(bandsAmountSlider.getValue());
        addElements(firstCombo, model1, renderer1, 5, 1);
        addElements(secondCombo, model2,renderer2, 5, 2);
        addElements(thirdCombo, model3, renderer3, 5, 3);
        addElements(forthCombo, model4, renderer4, 5, 4);
        addElements(fifthCombo, model5, renderer5, 5, 5);
        addElements(sixthCombo, model6, renderer6, 5, 6);
        setLabelStatus(ohmLabel, false);
        setLabelStatus(plusMinusLabel, false);
        setLabelStatus(temperatureLabel, false);
        setLabelStatus(toleranceLabel, false);
        setLabelStatus(percentageLabel, false);
        setLabelStatus(kelvinLabel, false);
        
       
        setResistor4(false);
        setResistor5(true);
        setResistor6(false);
        
        checkButton.setEnabled(false);
        resistanceNameLabel.setVisible(false);
        
    }
    
    private void addElements(JComboBox combo, DefaultComboBoxModel model, Renderer renderer, int mode, int position)
    {
        combo.removeAllItems();
        combo.setModel(model);
        
        int counter = 0;
        
        renderer.setMode(mode);
        renderer.setPosition(position);
        
        
        try
        {
            combo.setRenderer(renderer);
          //  setElement(combo);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        if((mode >= 5 && (position >= 1 && position <= 3))  ||  (mode==4 && (position == 1 || position == 2))) counter = 10;
        else if((mode >= 5 && position == 4) || (mode == 4 && position == 3)) counter = 12;
        else /*if((mode >= 5 && position == 5) || (mode == 4 && position == 4))*/ counter = 9;
        //else counter = 4;
        
       for(int i = 0; i<counter; i++)
       {
       if(((mode >= 5 && position == 5) && i == counter - 1)) combo.addItem(" NONE ");
       else combo.addItem("                                     ");
       }
    }
    
   
    
    private void setElement(JComboBox combo, Renderer renderer)
    {
        combo.setBackground(renderer.getColor());
        value1 = renderer.getValue();
    }
    
    private void showCombo(int value)
    {
        if(value == 4)
        {
            fifthCombo.setEnabled(false);
            sixthCombo.setEnabled(false);
            fifthDigitLabel.setEnabled(false);
            sixthDigitLabel.setEnabled(false);
        }
        else if(value == 5)
        {
            fifthCombo.setEnabled(true);
            sixthCombo.setEnabled(false);
            fifthDigitLabel.setEnabled(true);
            sixthDigitLabel.setEnabled(false);
        }
        else
        {
            fifthCombo.setEnabled(true);
            sixthCombo.setEnabled(true);
            fifthDigitLabel.setEnabled(true);
            sixthDigitLabel.setEnabled(true);
        }
        
    }
    
    private void multiplier(double value, int mode)
    {
        switch(mode)
        {
            case 4: 
            {
                if(value == 0.01) resistance.insert(0, "0.");
                else if(value == 0.1) resistance.insert(1, ".");
                break;
            }
            default:
            {
                if(value == 0.01) resistance.insert(1, ".");
                else if(value == 0.1) resistance.insert(2, ".");
            }
        
        }
        
        if(value != 0.01 && value != 0.1)
        {
        switch ((int)value) {
            case 1:
                ;
                break;
            case 10:
                resistance.append("0");
                break;
            case 100:
                resistance.append("00");
                break;
            case 1000:
                resistance.append(" k");
                break;
            case 10000:
                resistance.append("0 k");
                break;
            case 100000:
                resistance.append("00 k");
                break;
            case 1000000:
                resistance.append(" kk");
                break;
            case 10000000:
                resistance.append("0 kk");
                break;
            case 100000000:
                resistance.append("00 kk");
                break;
            default:
                resistance.append(" kkk");
                break;
        }
        }
    }
    
    private void setLabelStatus(JLabel label, boolean value)
    {
        label.setVisible(value);
    }
    
    
    
    private String getResistance(int mode, double v1, double v2, double v3, double v4)
    {
        
            resistance.append((int)v1);
            resistance.append((int)v2);
            if(mode != 4) 
            {
                resistance.append((int) v3);
                multiplier(v4, mode);
            }
            else multiplier(v3, mode);
            
        return resistance.toString();
    }
    
    private String getTolerance(double value)
    {
        StringBuffer toleranceString = new StringBuffer();
        toleranceString.append(value);
        return toleranceString.toString();
    }
    
    private String getTemperature(double value)
    {
        StringBuffer temperatureString = new StringBuffer();
        temperatureString.append(value);
        return temperatureString.toString();        
    }

    private void setResistor4(boolean value)
    {
        firstRes4Label.setVisible(value);
        secondRes4Label.setVisible(value);
        thirdRes4Label.setVisible(value);
        forthRes4Label.setVisible(value);
    }
    
    private void setResistor5(boolean value)
    {
        firstRes5Label.setVisible(value);
        secondRes5Label.setVisible(value);
        thirdRes5Label.setVisible(value);
        forthRes5Label.setVisible(value);
        fifthRes5Label.setVisible(value);
    }
    
    private void setResistor6(boolean value)
    {
        firstRes6Label.setVisible(value);
        secondRes6Label.setVisible(value);
        thirdRes6Label.setVisible(value);
        forthRes6Label.setVisible(value);
        fifthRes6Label.setVisible(value);
        sixthRes6Label.setVisible(value);
    }
    
    private void setLabelColor(JLabel label, Color color)
    {
        label.setBackground(color);
    }
    
    private JLabel getActualLabel(String value)
    {
        JLabel tempLabel = new JLabel();
        StringBuffer temporaryString = new StringBuffer();
        String temp2 = "";
        
        temporaryString.append(value);
        
        switch(bandsAmountSlider.getValue())
        {
            case 4:
            {
                temp2 = "Res4Label";
                break;
            }
            case 5:
            {
                temp2 = "Res5Label";
                break;
            }
            default: temp2 = "Res6Label";
        }
        
        temporaryString.append(temp2);
       
        tempLabel.setName(temporaryString.toString());
        
        
        return tempLabel;
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bandsAmountSlider = new javax.swing.JSlider();
        picturePanel = new javax.swing.JPanel();
        sixthRes6Label = new javax.swing.JLabel();
        firstRes5Label = new javax.swing.JLabel();
        firstRes4Label = new javax.swing.JLabel();
        firstRes6Label = new javax.swing.JLabel();
        fifthRes6Label = new javax.swing.JLabel();
        fifthRes5Label = new javax.swing.JLabel();
        forthRes6Label = new javax.swing.JLabel();
        forthRes5Label = new javax.swing.JLabel();
        forthRes4Label = new javax.swing.JLabel();
        thirdRes6Label = new javax.swing.JLabel();
        thirdRes5Label = new javax.swing.JLabel();
        thirdRes4Label = new javax.swing.JLabel();
        secondRes5Label = new javax.swing.JLabel();
        secondRes4Label = new javax.swing.JLabel();
        secondRes6Label = new javax.swing.JLabel();
        resistorLabel = new javax.swing.JLabel();
        digitPanel = new javax.swing.JPanel();
        firstDigitLabel = new javax.swing.JLabel();
        secondDigitLabel = new javax.swing.JLabel();
        firstCombo = new javax.swing.JComboBox<>();
        thirdCombo = new javax.swing.JComboBox<>();
        secondCombo = new javax.swing.JComboBox<>();
        thirdDigitLabel = new javax.swing.JLabel();
        fifthDigitLabel = new javax.swing.JLabel();
        sixthDigitLabel = new javax.swing.JLabel();
        forthDigitLabel = new javax.swing.JLabel();
        forthCombo = new javax.swing.JComboBox<>();
        fifthCombo = new javax.swing.JComboBox<>();
        sixthCombo = new javax.swing.JComboBox<>();
        buttonPanel = new javax.swing.JPanel();
        checkButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        resultPanel = new javax.swing.JPanel();
        ohmLabel = new javax.swing.JLabel();
        resistanceLabel = new javax.swing.JLabel();
        toleranceLabel = new javax.swing.JLabel();
        percentageLabel = new javax.swing.JLabel();
        plusMinusLabel = new javax.swing.JLabel();
        temperatureLabel = new javax.swing.JLabel();
        kelvinLabel = new javax.swing.JLabel();
        resistanceNameLabel = new javax.swing.JLabel();

        jLabel1.setText("SET AMOUNT OF BANDS:");

        bandsAmountSlider.setMajorTickSpacing(1);
        bandsAmountSlider.setMaximum(6);
        bandsAmountSlider.setMinimum(4);
        bandsAmountSlider.setPaintLabels(true);
        bandsAmountSlider.setPaintTicks(true);
        bandsAmountSlider.setSnapToTicks(true);
        bandsAmountSlider.setValue(5);
        bandsAmountSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bandsAmountSliderStateChanged(evt);
            }
        });

        picturePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sixthRes6Label.setBackground(new java.awt.Color(255, 0, 0));
        sixthRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        sixthRes6Label.setOpaque(true);
        picturePanel.add(sixthRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 38, 23, 175));

        firstRes5Label.setBackground(new java.awt.Color(255, 0, 51));
        firstRes5Label.setForeground(new java.awt.Color(255, 51, 51));
        firstRes5Label.setOpaque(true);
        picturePanel.add(firstRes5Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 38, 23, 175));

        firstRes4Label.setBackground(new java.awt.Color(255, 0, 51));
        firstRes4Label.setForeground(new java.awt.Color(255, 51, 51));
        firstRes4Label.setOpaque(true);
        picturePanel.add(firstRes4Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 41, 23, 167));

        firstRes6Label.setBackground(new java.awt.Color(255, 0, 51));
        firstRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        firstRes6Label.setOpaque(true);
        picturePanel.add(firstRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 37, 23, 175));

        fifthRes6Label.setBackground(new java.awt.Color(255, 0, 51));
        fifthRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        fifthRes6Label.setOpaque(true);
        picturePanel.add(fifthRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 54, 24, 142));

        fifthRes5Label.setBackground(new java.awt.Color(255, 0, 51));
        fifthRes5Label.setForeground(new java.awt.Color(255, 51, 51));
        fifthRes5Label.setOpaque(true);
        picturePanel.add(fifthRes5Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 54, 24, 142));

        forthRes6Label.setBackground(new java.awt.Color(255, 0, 51));
        forthRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        forthRes6Label.setOpaque(true);
        picturePanel.add(forthRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 54, 23, 142));

        forthRes5Label.setBackground(new java.awt.Color(255, 0, 51));
        forthRes5Label.setForeground(new java.awt.Color(255, 51, 51));
        forthRes5Label.setOpaque(true);
        picturePanel.add(forthRes5Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 54, 23, 142));

        forthRes4Label.setBackground(new java.awt.Color(255, 0, 51));
        forthRes4Label.setForeground(new java.awt.Color(255, 51, 51));
        forthRes4Label.setOpaque(true);
        picturePanel.add(forthRes4Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 58, 23, 134));

        thirdRes6Label.setBackground(new java.awt.Color(255, 0, 51));
        thirdRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        thirdRes6Label.setOpaque(true);
        picturePanel.add(thirdRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 54, 23, 142));

        thirdRes5Label.setBackground(new java.awt.Color(255, 0, 51));
        thirdRes5Label.setForeground(new java.awt.Color(255, 51, 51));
        thirdRes5Label.setOpaque(true);
        picturePanel.add(thirdRes5Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 54, 23, 142));

        thirdRes4Label.setBackground(new java.awt.Color(255, 0, 51));
        thirdRes4Label.setForeground(new java.awt.Color(255, 51, 51));
        thirdRes4Label.setOpaque(true);
        picturePanel.add(thirdRes4Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 58, 23, 134));

        secondRes5Label.setBackground(new java.awt.Color(255, 0, 51));
        secondRes5Label.setForeground(new java.awt.Color(255, 51, 51));
        secondRes5Label.setOpaque(true);
        picturePanel.add(secondRes5Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 54, 23, 142));

        secondRes4Label.setBackground(new java.awt.Color(255, 0, 51));
        secondRes4Label.setForeground(new java.awt.Color(255, 51, 51));
        secondRes4Label.setOpaque(true);
        picturePanel.add(secondRes4Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 58, 23, 134));

        secondRes6Label.setBackground(new java.awt.Color(255, 0, 51));
        secondRes6Label.setForeground(new java.awt.Color(255, 51, 51));
        secondRes6Label.setOpaque(true);
        picturePanel.add(secondRes6Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 54, 23, 142));

        resistorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/res5.png"))); // NOI18N
        picturePanel.add(resistorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 560, 190));

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bandsAmountSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bandsAmountSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        firstDigitLabel.setText("1st BAND:");

        secondDigitLabel.setText("2nd BAND:");

        firstCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        firstCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstComboActionPerformed(evt);
            }
        });

        thirdCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        thirdCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thirdComboActionPerformed(evt);
            }
        });

        secondCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        secondCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondComboActionPerformed(evt);
            }
        });

        thirdDigitLabel.setText("3rd BAND:");

        fifthDigitLabel.setText("5th BAND:");

        sixthDigitLabel.setText("6th DIGIT:");

        forthDigitLabel.setText("4th BAND:");

        forthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        forthCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forthComboActionPerformed(evt);
            }
        });

        fifthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fifthCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fifthComboActionPerformed(evt);
            }
        });

        sixthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sixthCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixthComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout digitPanelLayout = new javax.swing.GroupLayout(digitPanel);
        digitPanel.setLayout(digitPanelLayout);
        digitPanelLayout.setHorizontalGroup(
            digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(digitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(digitPanelLayout.createSequentialGroup()
                        .addComponent(firstDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(digitPanelLayout.createSequentialGroup()
                        .addComponent(secondDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secondCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(digitPanelLayout.createSequentialGroup()
                        .addComponent(thirdDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thirdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fifthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sixthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sixthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fifthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        digitPanelLayout.setVerticalGroup(
            digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(digitPanelLayout.createSequentialGroup()
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fifthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fifthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(digitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thirdDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thirdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sixthDigitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sixthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        checkButton.setText("CHECK");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
            }
        });

        exitButton.setText("EXIT");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        ohmLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ohmLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ohmLabel.setText("Ω");

        resistanceLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        resistanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        toleranceLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        toleranceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        percentageLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        percentageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percentageLabel.setText("%");

        plusMinusLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        plusMinusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        plusMinusLabel.setText("+/-");

        temperatureLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        temperatureLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        kelvinLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        kelvinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kelvinLabel.setText("10^-6 / K");

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resistanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohmLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plusMinusLabel)
                .addGap(2, 2, 2)
                .addComponent(toleranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(percentageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(temperatureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kelvinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resistanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toleranceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(percentageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(temperatureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kelvinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(ohmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plusMinusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        resistanceNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        resistanceNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resistanceNameLabel.setText("RESISTANCE:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(resistanceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(digitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resistanceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(digitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtonActionPerformed
        // TODO add your handling code here:
       // System.out.println(renderer1.getIndex(firstCombo.getBackground()));
        
        resistanceNameLabel.setVisible(true);
       
        resistance.delete(0, resistance.length());
        
       
        value1 = renderer1.getIndex(firstCombo.getBackground(), bandsAmountSlider.getValue(),1);
        value2 = renderer2.getIndex(secondCombo.getBackground(), bandsAmountSlider.getValue(),2);
        value3 = renderer3.getIndex(thirdCombo.getBackground(),bandsAmountSlider.getValue(),3);
        value4 = renderer4.getIndex(forthCombo.getBackground(), bandsAmountSlider.getValue(),4);
        
        if(bandsAmountSlider.getValue() >= 5) value5 = renderer5.getIndex(fifthCombo.getBackground(), bandsAmountSlider.getValue(),5);
        if(bandsAmountSlider.getValue() == 6) value6 = renderer6.getIndex(sixthCombo.getBackground(), bandsAmountSlider.getValue(),6);
        
        resistanceLabel.setText(getResistance(bandsAmountSlider.getValue(), value1, value2, value3, value4));
        
        if(bandsAmountSlider.getValue() == 4) toleranceLabel.setText(getTolerance(value4));
        else toleranceLabel.setText(getTolerance(value5));
        
        if(bandsAmountSlider.getValue() == 6) temperatureLabel.setText(getTemperature(value6));
        
        setLabelStatus(ohmLabel, true);
        setLabelStatus(plusMinusLabel, true);
        setLabelStatus(toleranceLabel, true);
        setLabelStatus(percentageLabel, true);
        if(bandsAmountSlider.getValue() == 6)
        {
            setLabelStatus(kelvinLabel, true);
            setLabelStatus(temperatureLabel, true);
        }
        
        
        
      //  System.out.println(getResistance(bandsAmountSlider.getValue()));
        
    }//GEN-LAST:event_checkButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void bandsAmountSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bandsAmountSliderStateChanged
        // TODO add your handling code here:
        showCombo(bandsAmountSlider.getValue());
        addElements(firstCombo, model1, renderer1, bandsAmountSlider.getValue(), 1);
        addElements(secondCombo, model2, renderer2, bandsAmountSlider.getValue(), 2);
        addElements(thirdCombo, model3, renderer3, bandsAmountSlider.getValue(), 3);
        addElements(forthCombo, model4, renderer4, bandsAmountSlider.getValue(), 4);
        addElements(fifthCombo, model5, renderer5, bandsAmountSlider.getValue(), 5);
        addElements(sixthCombo, model6, renderer6, bandsAmountSlider.getValue(), 6);
        
        switch(bandsAmountSlider.getValue())
        {
            case 4:
            {
             resistorLabel.setIcon(resistor4);
                setResistor4(true);
                setResistor5(false);
                setResistor6(false);
             
                break;
            }
            case 5:
            {
                resistorLabel.setIcon(resistor5);
                setResistor4(false);
                setResistor5(true);
                setResistor6(false);
                
                break;
            }
            default: 
            {
                resistorLabel.setIcon(resistor6);
                setResistor4(false);
                setResistor5(false);
                setResistor6(true);
            }
        }
        
        
    }//GEN-LAST:event_bandsAmountSliderStateChanged

    private void firstComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstComboActionPerformed
        // TODO add your handling code here:
     //s   value1 = model1.getIndexOf();
        checkButton.setEnabled(true);
        setElement(firstCombo, renderer1);
        
        JLabel temp;  
        
        switch(bandsAmountSlider.getValue())
        {
            case 4: 
            {
                temp = firstRes4Label;
                break;
            }
            case 5:
            {
                temp = firstRes5Label;
                break;
            }
            default:
                temp = firstRes6Label;
        }
        
        setLabelColor(temp, renderer1.getColor());
    }//GEN-LAST:event_firstComboActionPerformed

    private void secondComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondComboActionPerformed
        // TODO add your handling code here:
        checkButton.setEnabled(true);
        value2 = secondCombo.getSelectedIndex();
        setElement(secondCombo, renderer2);
        
        JLabel temp;
        
        switch(bandsAmountSlider.getValue())
        {
            case 4: 
            {
                temp = secondRes4Label;
                break;
            }
            case 5:
            {
                temp = secondRes5Label;
                break;
            }
            default:
                temp = secondRes6Label;
        }
        
        setLabelColor(temp, renderer2.getColor());
    }//GEN-LAST:event_secondComboActionPerformed

    private void thirdComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thirdComboActionPerformed
        // TODO add your handling code here:
        checkButton.setEnabled(true);
        value3 = thirdCombo.getSelectedIndex();
        setElement(thirdCombo, renderer3);
        
        JLabel temp;
        
        switch(bandsAmountSlider.getValue())
        {
            case 4: 
            {
                temp = thirdRes4Label;
                break;
            }
            case 5:
            {
                temp = thirdRes5Label;
                break;
            }
            default:
                temp = thirdRes6Label;
        }
        
        setLabelColor(temp, renderer3.getColor());
    }//GEN-LAST:event_thirdComboActionPerformed

    private void forthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forthComboActionPerformed
        // TODO add your handling code here:
        checkButton.setEnabled(true);
        value4 = forthCombo.getSelectedIndex();
        setElement(forthCombo, renderer4);
        
        JLabel temp;
        
        switch(bandsAmountSlider.getValue())
        {
            case 4: 
            {
                temp = forthRes4Label;
                break;
            }
            case 5:
            {
                temp = forthRes5Label;
                break;
            }
            default:
                temp = forthRes6Label;
        }
        
        setLabelColor(temp, renderer4.getColor());
        
        
    }//GEN-LAST:event_forthComboActionPerformed

    private void sixthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixthComboActionPerformed
        // TODO add your handling code here:
        checkButton.setEnabled(true);
        setElement(sixthCombo, renderer6);
        
      
        setLabelColor(sixthRes6Label, renderer6.getColor());
        
    }//GEN-LAST:event_sixthComboActionPerformed

    private void fifthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fifthComboActionPerformed
        // TODO add your handling code here:
        checkButton.setEnabled(true);
        value5 = fifthCombo.getSelectedIndex();
        setElement(fifthCombo, renderer5);
        
        JLabel temp;
        
        switch(bandsAmountSlider.getValue())
        {
            case 5:
            {
                temp = fifthRes5Label;
                break;
            }
            default:
                temp = fifthRes6Label;
        }
        
        setLabelColor(temp, renderer5.getColor());
    }//GEN-LAST:event_fifthComboActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider bandsAmountSlider;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton checkButton;
    private javax.swing.JPanel digitPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JComboBox<String> fifthCombo;
    private javax.swing.JLabel fifthDigitLabel;
    private javax.swing.JLabel fifthRes5Label;
    private javax.swing.JLabel fifthRes6Label;
    private javax.swing.JComboBox<String> firstCombo;
    private javax.swing.JLabel firstDigitLabel;
    private javax.swing.JLabel firstRes4Label;
    private javax.swing.JLabel firstRes5Label;
    private javax.swing.JLabel firstRes6Label;
    private javax.swing.JComboBox<String> forthCombo;
    private javax.swing.JLabel forthDigitLabel;
    private javax.swing.JLabel forthRes4Label;
    private javax.swing.JLabel forthRes5Label;
    private javax.swing.JLabel forthRes6Label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel kelvinLabel;
    private javax.swing.JLabel ohmLabel;
    private javax.swing.JLabel percentageLabel;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JLabel plusMinusLabel;
    private javax.swing.JLabel resistanceLabel;
    private javax.swing.JLabel resistanceNameLabel;
    private javax.swing.JLabel resistorLabel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JComboBox<String> secondCombo;
    private javax.swing.JLabel secondDigitLabel;
    private javax.swing.JLabel secondRes4Label;
    private javax.swing.JLabel secondRes5Label;
    private javax.swing.JLabel secondRes6Label;
    private javax.swing.JComboBox<String> sixthCombo;
    private javax.swing.JLabel sixthDigitLabel;
    private javax.swing.JLabel sixthRes6Label;
    private javax.swing.JLabel temperatureLabel;
    private javax.swing.JComboBox<String> thirdCombo;
    private javax.swing.JLabel thirdDigitLabel;
    private javax.swing.JLabel thirdRes4Label;
    private javax.swing.JLabel thirdRes5Label;
    private javax.swing.JLabel thirdRes6Label;
    private javax.swing.JLabel toleranceLabel;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables
}
