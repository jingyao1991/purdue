import javax.swing.*; 
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*;

public class EditorMenu extends JMenuBar
{
    private JMenuBar _menu;
    private JMenu _fileMenu;
    private JMenu _viewMenu;
    private JMenu _modeMenu;

    public EditorMenu(JFrame window, ActionListener callback)
    {
        setupFileMenu(callback);
        setupViewMenu(callback);
        setupModeMenu(callback);
        
        _menu = new JMenuBar();
                
        window.setJMenuBar(_menu);
        _menu.add(_fileMenu);
        _menu.add(_viewMenu);
        _menu.add(_modeMenu);
    }
    
    protected void setupFileMenu(ActionListener callback)
    {
        _fileMenu = new JMenu("File");
        
        JMenuItem item;
        
        item = new JMenuItem("New");
        item.addActionListener(callback);
        _fileMenu.add(item);
        
        item = new JMenuItem("Open...");
        item.addActionListener(callback);
        _fileMenu.add(item);
        
        item = new JMenuItem("Save");
        item.addActionListener(callback);
        _fileMenu.add(item);
        
        item = new JMenuItem("Save As...");
        item.addActionListener(callback);
        _fileMenu.add(item);
        
        item = new JMenuItem("Exit");
        item.addActionListener(callback);
        _fileMenu.add(item);
    }
    
    protected void setupViewMenu(ActionListener callback)
    {
        _viewMenu = new JMenu("View");
        
        JMenuItem item;
        
        item = new JMenuItem("Zoom In");
        item.addActionListener(callback);
        _viewMenu.add(item);
        
        item = new JMenuItem("Zoom Out");
        item.addActionListener(callback);
        _viewMenu.add(item);
    }
    
    protected void setupModeMenu(ActionListener callback)
    {
        _modeMenu = new JMenu("Mode");
        
        JMenuItem item;
        
        item = new JMenuItem("Insert Location");
        item.addActionListener(callback);
        _modeMenu.add(item);
        
        item = new JMenuItem("Insert Path");
        item.addActionListener(callback);
        _modeMenu.add(item);
        
        item = new JMenuItem("Delete Location");
        item.addActionListener(callback);
        _modeMenu.add(item);
        
        item = new JMenuItem("Delete Path");
        item.addActionListener(callback);
        _modeMenu.add(item);
        
        item = new JMenuItem("Show Properties");
        item.addActionListener(callback);
        _modeMenu.add(item);
    }
}