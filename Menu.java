import javax.swing.*;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menu Example");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem herramientasItem = new JMenuItem("ToolBar");
        JMenuItem statusbarItem = new JMenuItem("StatusBar");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem welcomeItem = new JMenuItem("Welcome");
        JMenuItem documentationItem = new JMenuItem("Documentation");
        JMenuItem createDocumentItem = new JMenuItem("Create new document");
        JMenuItem checkBoxItem = new JMenuItem("Check Box");

        addMenuAction(newItem, "You create a new document");
        addMenuAction(openItem, "You open a document");
        addMenuAction(cutItem, "You cut the text");
        addMenuAction(copyItem, "You copy the text");
        addMenuAction(herramientasItem, "You open the tool bar");
        addMenuAction(statusbarItem, "You open the status bar");
        addMenuAction(aboutItem, "You open the about dialog");
        addMenuAction(welcomeItem, "Welcome");
        addMenuAction(documentationItem, "You open the documentation");
        addMenuAction(createDocumentItem, "You create a new document");
        addMenuAction(checkBoxItem, "You are here in the check box");

        exitItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        editMenu.add(cutItem);
        editMenu.addSeparator();
        editMenu.add(copyItem);

        viewMenu.add(herramientasItem);
        viewMenu.addSeparator();
        viewMenu.add(statusbarItem);
        viewMenu.addSeparator();
        viewMenu.add(aboutItem);

        helpMenu.add(welcomeItem);
        helpMenu.addSeparator();
        helpMenu.add(documentationItem);

        toolsMenu.add(createDocumentItem);
        toolsMenu.addSeparator();
        toolsMenu.add(checkBoxItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        menuBar.add(toolsMenu);
        menuBar.add(Box.createHorizontalGlue());
        setJMenuBar(menuBar);
    }

    private void addMenuAction(JMenuItem item, String message) {
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menuExample = new Menu();
            menuExample.setVisible(true);
        });
    }
}