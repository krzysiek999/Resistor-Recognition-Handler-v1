/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author krzys
 */
public class Renderer extends JLabel implements ListCellRenderer
{
    
    DefaultComboBoxModel model;
    int mode = 0, position = 0;
    int fontSize = 21;
    
    public Renderer(DefaultComboBoxModel model)
            {
                super();
                setOpaque(true);
                this.model = model;
            }
    
    public void setMode(int value)
    {
        this.mode = value;
    }
    
    public void setPosition(int value)
    {
        this.position = value;
    }
    
    public int getPosition()
    {
        return this.position;
    }
    
    public int getMode()
    {
        return this.mode;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
    {
        
        setText(value.toString());
        
        
        if((getMode() >= 5 && (getPosition() >= 1 && getPosition() <= 3))  ||  (getMode() == 4 && (position == 1 || position == 2)))
        {
            switch (index) {
                case 0:
                    setBackground(Color.BLACK);
                    break;
                case 1:
                    setBackground(new Color(165,0,0));
                    break;
                case 2:
                    setBackground(Color.RED);
                    break;
                case 3:
                    setBackground(new Color(255,102,0));
                    break;
                case 4:
                    setBackground(Color.YELLOW);
                    break;
                case 5:
                    setBackground(Color.GREEN);
                    break;
                case 6:
                    setBackground(Color.BLUE);
                    break;
                case 7:
                    setBackground(Color.MAGENTA);
                    break;
                case 8:
                    setBackground(Color.GRAY);
                    break;
                case 9:
                    setBackground(Color.WHITE);
                    break;
                default:
                    break;
            }
        }
        
        if((getMode() >= 5 && getPosition() == 4)  ||  (getMode() == 4 && position == 3))
        {
            switch (index) {
                case 0:
                    setBackground(Color.LIGHT_GRAY);
                    break;
                case 1:
                    setBackground(new Color(255,204,0));
                    break;
                case 2:
                    setBackground(Color.BLACK);
                    break;
                case 3:
                    setBackground(new Color(165,0,0));
                    break;
                case 4:
                    setBackground(Color.RED);
                    break;
                case 5:
                    setBackground(new Color(255,102,0));
                    break;
                case 6:
                    setBackground(Color.YELLOW);
                    break;
                case 7:
                    setBackground(Color.GREEN);
                    break;
                case 8:
                    setBackground(Color.BLUE);
                    break;
                case 9:
                    setBackground(Color.MAGENTA);
                    break;
                case 10:
                    setBackground(Color.GRAY);
                    break;
                case 11:
                    setBackground(Color.WHITE);
                    break;
                default:
                    break;
            }
        }
        
        if((getMode() >= 5 && getPosition() == 5)  ||  (getMode() == 4 && position == 4))
        {
            switch (index) {
                case 0:
                    setBackground(Color.LIGHT_GRAY);
                    break;
                case 1:
                    setBackground(new Color(255,204,0));
                    break;
                case 2:
                    setBackground(new Color(165,0,0));
                    break;
                case 3:
                    setBackground(Color.RED);
                    break;
                case 4:
                    setBackground(Color.GREEN);
                    break;
                case 5:
                    setBackground(Color.BLUE);
                    break;
                case 6:
                    setBackground(Color.MAGENTA);
                    break;
                case 7:
                    setBackground(Color.GRAY);
                    break;
                case 8:
                    setBackground(Color.WHITE);
                    break;
                            
                default:
                    break;
            }
        }
        
        if(getMode() >= 5 && getPosition() == 6)
        {
            
              //  case 0:
                 //   setBackground(Color.BLACK);
                    
             switch (index) {
                case 0:
                    setBackground(Color.BLACK);
                    break;
                case 1:
                    setBackground(new Color(165,0,0));
                    break;
                case 2:
                    setBackground(Color.RED);
                    break;
                case 3:
                    setBackground(new Color(255,102,0));
                    break;
                case 4:
                    setBackground(Color.YELLOW);
                    break;
                case 5:
                    setBackground(Color.GREEN);
                    break;
                case 6:
                    setBackground(Color.BLUE);
                    break;
                case 7:
                    setBackground(Color.MAGENTA);
                    break;
                case 8:
                    setBackground(Color.GRAY);
                    break;
                case 9:
                    setBackground(Color.WHITE);
                    break;
                default:
                    break;
            }
        }
        
        setFont(new Font("Courier", Font.ITALIC, fontSize));
        
        return this;
    }
    
    public Color getColor()
    {
        return this.getBackground();
    }
    
    public double getIndex(Color color, int mode, int position)
    {
        
        if((mode == 4 && (position == 1 || position == 2)) || (mode >= 5 && (position >= 1 && position <= 3)))
        {
            if(color.equals(Color.BLACK)) return 0.0;
            else if(color.equals(new Color(165,0,0))) return 1.0;
            else if(color.equals(Color.RED)) return 2.0;
            else if(color.equals(new Color(255,102,0))) return 3.0;
            else if(color.equals(Color.YELLOW)) return 4.0;
            else if(color.equals(Color.GREEN)) return 5.0;
            else if(color.equals(Color.BLUE)) return 6.0;
            else if(color.equals(Color.MAGENTA)) return 7.0;
            else if(color.equals(Color.GRAY)) return 8.0;
            else  return 9.0;
        }
        
        if((mode == 4 && position == 3) || (mode == 5 && position == 4) || (mode == 6 && position == 4))
        {
            if(color.equals(Color.LIGHT_GRAY)) return 0.01;
        else if(color.equals(new Color(255,204,0))) return 0.1;
        else if(color.equals(Color.BLACK)) return 1.0;
        else if(color.equals(new Color(165,0,0))) return 10.0;
        else if(color.equals(Color.RED)) return 100.0;
        else if(color.equals(new Color(255,102,0))) return 1000.0;
        else if(color.equals(Color.YELLOW)) return 10000.0;
        else if(color.equals(Color.GREEN)) return 100000.0;
        else if(color.equals(Color.BLUE)) return 1000000.0;
        else if(color.equals(Color.MAGENTA)) return 10000000.0;
        else if(color.equals(Color.GRAY)) return 100000000.0;
        else if(color.equals(Color.WHITE)) return 1000000000.0;    
        }
        
        if((mode == 4 && position == 4) || (mode >= 5 && position == 5))
        {
            if(color.equals(Color.LIGHT_GRAY)) return 10.0;
        else if(color.equals(new Color(255,204,0))) return 5.0;
        else if(color.equals(Color.WHITE)) return 20.0;
        else if(color.equals(new Color(165,0,0))) return 1.0;
        else if(color.equals(Color.RED)) return 2.0;
        else if(color.equals(Color.GREEN)) return 0.5;
        else if(color.equals(Color.BLUE)) return 0.25;
        else if(color.equals(Color.MAGENTA)) return 0.1;
        else if(color.equals(Color.GRAY)) return 0.05;
        }
        
        if(mode == 6 && position == 6)
        {
        if(color.equals(Color.BLACK)) return 250.0;
            else if(color.equals(new Color(165,0,0))) return 100.0;
            else if(color.equals(Color.RED)) return 50.0;
            else if(color.equals(new Color(255,102,0))) return 15.0;
            else if(color.equals(Color.YELLOW)) return 25.0;
            else if(color.equals(Color.GREEN)) return 20.0;
            else if(color.equals(Color.BLUE)) return 10.0;
            else if(color.equals(Color.MAGENTA)) return 5.0;
            else  return 1.0; 
        }
        
        return 0;
    }
    
    public int getValue()
    {
        return model.getIndexOf(model.getSelectedItem());
    }
    
}
