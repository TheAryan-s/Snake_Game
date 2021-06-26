package JavaLabProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private static final long serialVersionUID = 3425489839546331043L;
    private ImageIcon downmouth;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    // why are we assigning them as false ?

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lengthOfSnake = 3;
    private int moves = 0;

    private Timer timer;
    private int delay =150;

    private ImageIcon snakeimage;

    private int[] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyYpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,600,625};
    private ImageIcon enemyimage;

    private Random random = new Random();
    private int Xpos = random.nextInt(34); 
    private int Ypos = random.nextInt(23); 

    private int score = 0;

    private ImageIcon titleImage;
    public Gameplay() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();  
    }

    public void paint(Graphics g){
        
        if(moves == 0){
            snakeXlength[2]=50;
            snakeXlength[1]=75;
            snakeXlength[0]=100;

            snakeYlength[2]=100;
            snakeYlength[1]=100;
            snakeYlength[0]=100;
        }
 
        //To draw the title image
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        //To draw border for the gameplay 
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);

        //To draw background for the gameplay 
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        //To write/draw the scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: "+score, 780, 30);

        //To Write/draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: "+lengthOfSnake, 780, 50);

        //To add the image for the rightmouth of the snake.
        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

        //To differentiate between which mouth will show up and when, 
        //e.g; If the snake is heading towards right then rightmouth will be shown.
        for(int a=0; a< lengthOfSnake; a++){
            if (a==0 && right){
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a==0 && left){
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a==0 && up){
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a==0 && down){
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a!=0){
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
        }
        //For adding the enemy/food image for the snake.
        enemyimage = new ImageIcon("enemy.png");
        if((enemyXpos[Xpos]==snakeXlength[0]) && (enemyYpos[Ypos] == snakeYlength[0])){
            score++;
            lengthOfSnake++;
        Xpos = random.nextInt(34);
        Ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyXpos[Xpos], enemyYpos[Ypos]);

        //Whatif snake head collides with his/her own body.
        //Unfortunately, "GAME OVER".
        //Press SpaceBar to restart the Game.
        for(int b =1;b<lengthOfSnake; b++){
            if(snakeXlength[b]== snakeXlength[0] && snakeYlength[b] == snakeYlength[0]){
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Spacebar to RESTART", 350, 340);
            }
        }
            g.dispose();
    }
    //These are the unimplemented methods.

    //For movement of the whole body of the snake (by using the concept of arrays).
    @Override
    public void actionPerformed(ActionEvent arg0) {
        timer.start();
          if(right){
              for(int r = lengthOfSnake-1; r>=0 ; r--){
                  snakeYlength[r+1] = snakeYlength[r];
              }
              for(int r = lengthOfSnake; r>=0; r--){
                  if(r==0){
                      snakeXlength[r] = snakeXlength[r] + 25;
                  }
                  else{
                      snakeXlength[r] = snakeXlength[r-1];
                  }
                  if(snakeXlength[r]>850){
                      snakeXlength[r]=25;
                  }
              }
              repaint();
          }
           if(left){
            for(int r = lengthOfSnake-1; r>=0 ; r--){
                          snakeYlength[r+1] = snakeYlength[r];
                      }
                      for(int r = lengthOfSnake; r>=0; r--){
                          if(r==0){
                              snakeXlength[r] = snakeXlength[r] - 25;
                          }
                          else{
                              snakeXlength[r] = snakeXlength[r-1];
                          }
                          if(snakeXlength[r]<25){
                              snakeXlength[r]=850;
                          }
                      }
                      repaint();
          }
          if(up){
            for(int r = lengthOfSnake-1; r>=0 ; r--){
                snakeXlength[r+1] = snakeXlength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r==0){
                    snakeYlength[r] = snakeYlength[r] - 25;
                }
                else{
                    snakeYlength[r] = snakeYlength[r-1];
                }
                if(snakeYlength[r]<75){
                    snakeYlength[r]=625;
                }
            }
            repaint();
          }
          if(down){
            for(int r = lengthOfSnake-1; r>=0 ; r--){
                snakeXlength[r+1] = snakeXlength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r==0){
                    snakeYlength[r] = snakeYlength[r] + 25;
                }
                else{
                    snakeYlength[r] = snakeYlength[r-1];
                }
                if(snakeYlength[r]>625){
                    snakeYlength[r]=75;
                }
            }
            repaint();
          }    

    }
    //For the movement of the snake, i.e; towards right, left, up, down (by using the arrows key).
    @Override
    public void keyPressed(KeyEvent  arg0) {
        if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
                    moves = 0;
                    score = 0;
                    lengthOfSnake = 3;
                    repaint();
                }
                if(arg0.getKeyCode()== KeyEvent.VK_RIGHT){
                    moves++;
                    right = true;
    // Just in case if left arrow key is pressed when the snake was going in the right direction.
                    if(!left){
                        right = true;
                    }
                    else{
                        right = false;
                        left = true;
                    }
                    up = false;
                    down = false;
                }
                if(arg0.getKeyCode()== KeyEvent.VK_LEFT){
                    moves++;
                    left = true;
                    if(!right){
                        left = true;
                    }
                    else{
                        left = false;
                        right = true;
                    }
                    up = false;
                    down = false;
                }
                if(arg0.getKeyCode()== KeyEvent.VK_UP){
                    moves++;
                    up = true;
                    if(!down){
                        up = true;
                    }
                    else{
                        up = false;
                        down = true;
                    }
                    right = false;
                    left= false;
                }
                if(arg0.getKeyCode()== KeyEvent.VK_DOWN){
                    moves++;
                    down = true;
                    if(!up){
                        down = true;
                    }
                    else{
                        down = false;
                        up= true;
                    }
                    right = false;
                    left= false;
                }

    }
 
    //These are just the unimplemented methods created by the Ide.
    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
      
    }
}
