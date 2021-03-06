import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

class Queen extends JFrame{
    static int width ;
    static int height;
    
    static JPanel squares[][] = new JPanel[8][8];
    static JLabel lab;
    static Image image;
    static JButton newSolutionButton;
    static JButton exitButton;
            
    solution newSol = new solution();
    
    Queen(){
        
        setTitle("N Queen Problem");
        setSize(600,600);
        setLayout(new GridLayout(8, 8));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuItem();
        height = (getHeight() / 9 );
        width = (getWidth() / 9);
        
        drawChessBoard();
        
        
        try{
            image = ImageIO.read(getClass().getResource("Queen.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            
        } catch(IOException e){
            System.out.println("Pic not found");
        }
            
        setLocationRelativeTo(null);
        setVisible(true);
       
    }
    
    public void drawChessBoard(){
        
//        System.out.println("Height and width" + height + "\t" + width);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {                
                squares[i][j] = new JPanel(new BorderLayout());
                if ((i + j) % 2 == 0) {                    
                    squares[i][j].setBackground(Color.black);
                } else {
                    squares[i][j].setBackground(Color.white);
                }   
                add(squares[i][j]);
            }
        }
    }
    
    public void PopUpDialog(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "No Solutions found, "
                + "Find New Solution?", "Error",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (response) {
            case JOptionPane.NO_OPTION:
                System.out.println("No button clicked");
                System.exit(0);
                break;
            case JOptionPane.YES_OPTION:
                System.out.println("Yes button clicked");
                dispose();
                newSol.newSolution();
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("JOptionPane closed");
                break;
            default:
                break;
        }
    }
    
    
    public void MenuItem(){
        JMenuBar jmb = new JMenuBar();
        JMenu file = new JMenu("File");
        // newO = saves current text, open new "untitled" Notepad
        JMenuItem newSolution = new JMenuItem("New Solution"); 
        
        file.setMnemonic('F');
        newSolution.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        
        file.add(newSolution);
        jmb.add(file);
        setJMenuBar(jmb);
        
        newSolution.addActionListener(n ->{
            
            dispose();
            newSol.newSolution();
               
          });
    }
    
    public void DrawQueen(int QUEEN_SOLUTION[][]){
        
        for (int i = 0; i < QUEEN_SOLUTION.length; i++){
            for (int j = 0; j < QUEEN_SOLUTION[0].length; j++){
                if(QUEEN_SOLUTION[i][j] == 1){
                    squares[i][j].add(new JLabel(new ImageIcon(image)));
                }
            }
        }
    }
    
    
//    public static void main(String[] args) throws IOException{
//        
//       SwingUtilities.invokeLater(new Runnable(){
//               public void run(){
//                    new Queen();
//               }
//          });
//      
//    }
        
    
}
