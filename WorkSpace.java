import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class WorkSpace implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem OpenFile, SaveFile, NewFile, SaveAsFile, ExitFile;
    JMenuItem WordWrap, Arial, MVBoli, timesNewRoman, BookAntiqua, MSGothic, ComicSansMS;
    JMenu FontSize, Fonts;
    JMenuItem setSize8, setSize12, setSize16, setSize20, setSize24, setSize28, setSize32;
    JMenuItem White, Dark;
    JMenuItem UndoEdit, RedoEdit, CopyEdit, PasteEdit, CutEdit;

    String fileName = null;
    String fileDirectory = null;
    String selectedFont = "Arial";
    Boolean WordWrapOn = false;
    Font mvboli, arial, roman, book, gothic, comic;
    UndoManager um = new UndoManager();

    public static void main(String[] args) {
        new WorkSpace();
    }

    // Constructor
    public WorkSpace() {
        // frame

        frame = new JFrame("Notepad");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("notepad.png");
        frame.setIconImage(icon.getImage());

        // Text Area

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);

        //Key-listener
        textArea.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent k) {
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_S) {
                    SaveFile.doClick();
                }
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_N) {
                    NewFile.doClick();
                }
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_O) {
                    OpenFile.doClick();
                }
                if (k.isShiftDown() && k.isControlDown() && k.getKeyCode() == KeyEvent.VK_S) {
                    SaveAsFile.doClick();
                }
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_R) {
                    RedoEdit.doClick();
                }
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_Z) {
                    UndoEdit.doClick();
                }
                if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_E) {
                    ExitFile.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent k) {
                // Optional: Handle key release events if needed
            }

            @Override
            public void keyTyped(KeyEvent k) {
                // Optional: Handle key typed events if needed
            }
        });

        //undo-redo Listener
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent ev) {
                um.addEdit(ev.getEdit());
            }
        });

        // menu Bar

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");
        JMenu FormatMenu = new JMenu("Format");
        JMenu ThemeMenu = new JMenu("Theme");
        menuBar.add(fileMenu);
        menuBar.add(EditMenu);
        menuBar.add(FormatMenu);
        menuBar.add(ThemeMenu);

        // File-menuItems

        NewFile = new JMenuItem("New       (ctrl+N)");
        NewFile.addActionListener(this);
        OpenFile = new JMenuItem("Open      (ctrl+O)");
        OpenFile.addActionListener(this);
        SaveFile = new JMenuItem("Save      (ctrl+S)");
        SaveFile.addActionListener(this);
        SaveAsFile = new JMenuItem("SaveAs (ctrl+shift+N)");
        SaveAsFile.addActionListener(this);
        ExitFile = new JMenuItem("Exit         (ctrl+E)");
        ExitFile.addActionListener(this);
        fileMenu.add(NewFile);
        fileMenu.add(OpenFile);
        fileMenu.add(SaveFile);
        fileMenu.add(SaveAsFile);
        fileMenu.add(ExitFile);

        // Edit-menuItems

        UndoEdit = new JMenuItem("Undo      (ctrl+z)");
        RedoEdit = new JMenuItem("Redo      (ctrl+r)");
        CopyEdit = new JMenuItem("Copy      (ctrl+c)");
        PasteEdit = new JMenuItem("Paste      (ctrl+v)");
        CutEdit = new JMenuItem("Cut        (ctrl+x)");
        CopyEdit.addActionListener(this);
        PasteEdit.addActionListener(this);
        CutEdit.addActionListener(this);
        UndoEdit.addActionListener(this);
        RedoEdit.addActionListener(this);
        EditMenu.add(UndoEdit);
        EditMenu.add(RedoEdit);
        EditMenu.add(CutEdit);
        EditMenu.add(CopyEdit);
        EditMenu.add(PasteEdit);

        // Format-menuItems

        WordWrap = new JMenuItem("Word Wrap:Off");
        WordWrap.addActionListener(this);
        Fonts = new JMenu("Fonts");
        Arial = new JMenuItem("Arial");
        Arial.addActionListener(this);
        MVBoli = new JMenuItem("MV Boli");
        MVBoli.addActionListener(this);
        timesNewRoman = new JMenuItem("Times New Roman");
        timesNewRoman.addActionListener(this);
        BookAntiqua = new JMenuItem("Book Antiqua");
        BookAntiqua.addActionListener(this);
        MSGothic = new JMenuItem("MS Gothic");
        MSGothic.addActionListener(this);
        ComicSansMS = new JMenuItem("Comic Sans MS");
        ComicSansMS.addActionListener(this);
        Fonts.add(Arial);
        Fonts.add(MVBoli);
        Fonts.add(timesNewRoman);
        Fonts.add(BookAntiqua);
        Fonts.add(ComicSansMS);
        Fonts.add(MSGothic);
        FontSize = new JMenu("Fontsize");
        setSize8 = new JMenuItem("8");
        setSize8.addActionListener(this);
        setSize12 = new JMenuItem("12");
        setSize12.addActionListener(this);
        setSize16 = new JMenuItem("16");
        setSize16.addActionListener(this);
        setSize20 = new JMenuItem("20");
        setSize20.addActionListener(this);
        setSize24 = new JMenuItem("24");
        setSize24.addActionListener(this);
        setSize28 = new JMenuItem("28");
        setSize28.addActionListener(this);
        setSize32 = new JMenuItem("32");
        setSize32.addActionListener(this);

        FontSize.add(setSize8);
        FontSize.add(setSize12);
        FontSize.add(setSize16);
        FontSize.add(setSize20);
        FontSize.add(setSize24);
        FontSize.add(setSize28);
        FontSize.add(setSize32);

        FormatMenu.add(WordWrap);
        FormatMenu.add(Fonts);
        FormatMenu.add(FontSize);

        // Theme-menuItems

        White = new JMenuItem("White");
        White.addActionListener(this);
        Dark = new JMenuItem("Dark");
        Dark.addActionListener(this);
        ThemeMenu.add(White);
        ThemeMenu.add(Dark);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        //constructor end
    }

    //ActionPerformed function

    public void actionPerformed(ActionEvent e) {

        // new

        if (e.getSource() == NewFile) {
            textArea.setText("");
            frame.setTitle("New");
            fileDirectory = null;
            fileName = null;
        }

        // open

        if (e.getSource() == OpenFile) {
            FileDialog fileDialog = new FileDialog(frame, "Open", FileDialog.LOAD);
            fileDialog.setVisible(true);
            if (fileDialog.getFile() != null) {
                fileName = fileDialog.getFile(); // name of file
                fileDirectory = fileDialog.getDirectory(); // path of file
                frame.setTitle(fileName);
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileDirectory + fileName));
                String line = null;
                textArea.setText(""); // BufferReader: A class which reads From a character input stream
                while ((line = bufferedReader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception t) {
                System.out.println("File not opened");
            }
        }

        // Save

        if (e.getSource() == SaveFile) {
            if (fileName == null) {
                saveAs();
            }

            else {
                try {
                    FileWriter fileWriter = new FileWriter(fileDirectory + fileName);
                    fileWriter.write(textArea.getText());
                    fileWriter.close();
                } catch (Exception t) {
                    System.out.println("Something went Wrong");
                }

            }

        }

        // Save As

        if (e.getSource() == SaveAsFile) {
            saveAs();

        }

        // Exit

        if (e.getSource() == ExitFile) {
            System.exit(0);

        }

        // Word wrap

        if (e.getSource() == WordWrap) {

            if (WordWrapOn == false) {
                WordWrapOn = true;
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                WordWrap.setText("Word Wrap:on");
            } else if (WordWrapOn == true) {
                WordWrapOn = false;
                textArea.setLineWrap(false);
                textArea.setWrapStyleWord(false);
                WordWrap.setText("Word Wrap:off");

            }

        }

        // Font Size

        if (e.getSource() == setSize8) {
            CreateFont(8);
        }
        if (e.getSource() == setSize12) {
            CreateFont(12);
        }
        if (e.getSource() == setSize16) {
            CreateFont(16);
        }
        if (e.getSource() == setSize20) {
            CreateFont(20);
        }
        if (e.getSource() == setSize24) {
            CreateFont(24);
        }
        if (e.getSource() == setSize28) {
            CreateFont(28);
        }
        if (e.getSource() == setSize32) {
            CreateFont(32);
        }

        // Font style

        if (e.getSource() == MVBoli) {
            CreateFont(textArea.getFont().getSize());
            setFonts("MV Boli");
        }
        if (e.getSource() == Arial) {
            CreateFont(textArea.getFont().getSize());
            setFonts("Arial");
        }
        if (e.getSource() == timesNewRoman) {
            CreateFont(textArea.getFont().getSize());
            setFonts("Times New Roman");
        }
        if (e.getSource() == BookAntiqua) {
            CreateFont(textArea.getFont().getSize());
            setFonts("Book Antiqua");
        }
        if (e.getSource() == ComicSansMS) {
            CreateFont(textArea.getFont().getSize());
            setFonts("Comic Sans MS");
        }
        if (e.getSource() == MSGothic) {
            CreateFont(textArea.getFont().getSize());
            setFonts("MS Gothic");
        }


        //Theme

        if (e.getSource() == White) {
            frame.getContentPane().setBackground(Color.white);
            textArea.setBackground(Color.white);
            textArea.setForeground(Color.black);
        }
        if (e.getSource() == Dark) {
            frame.getContentPane().setBackground(Color.black);
            textArea.setBackground(Color.black);
            textArea.setForeground(Color.white);
        }


        //Edit 


        if (e.getSource() == UndoEdit) {
            um.undo();
        }
        if (e.getSource() == RedoEdit) {
            um.redo();
        }
        if (e.getSource() == CutEdit) {
            textArea.cut();
        }
        if (e.getSource() == PasteEdit) {
            textArea.paste();
        }
        if (e.getSource() == CopyEdit) {
            textArea.copy();
        }

    //Action-Listener function end
    }

    //SaveAs Functuion

    public void saveAs() {
        FileDialog fileDialog = new FileDialog(frame, "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile(); // name of file
            fileDirectory = fileDialog.getDirectory(); // path of file
            frame.setTitle(fileName);
        }
        try {
            FileWriter fileWriter = new FileWriter(fileDirectory + fileName);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        } catch (Exception t) {
            System.out.println("Something went Wrong");
        }
    }

    //Fonts-function
    public void CreateFont(int size) {
        mvboli = new Font("MV Boli", Font.PLAIN, size);
        arial = new Font("Arial", Font.PLAIN, size);
        roman = new Font("Times New Roman", Font.PLAIN, size);
        book = new Font("Book Antiqua", Font.PLAIN, size);
        comic = new Font("Comic Sans MS", Font.PLAIN, size);
        gothic = new Font("MS Gothic", Font.PLAIN, size);
        setFonts(selectedFont);
    }

    //Fonts-function
    public void setFonts(String font) {
        selectedFont = font;
        switch (font) {
            case "Arial":
                textArea.setFont(arial);
                break;
            case "MV Boli":
                textArea.setFont(mvboli);
                break;
            case "Times New Roman":
                textArea.setFont(roman);
                break;
            case "Book Antiqua":
                textArea.setFont(book);
                break;
            case "MS Gothic":
                textArea.setFont(gothic);
                break;
            case "Comic Sans MS":
                textArea.setFont(comic);
                break;
            default:
                break;
        }
    }
//WorkSpace-Class end
}