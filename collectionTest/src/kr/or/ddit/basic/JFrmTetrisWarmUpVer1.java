package kr.or.ddit.basic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class JFrmTetrisWarmUpVer1 extends JFrame implements KeyListener
{ 
 
     Graphics bufferGraphics; 
     Image offscreen; 
     Dimension dim; //������Ʈ�� ũ�⸦ ��ȯ           
     
     Block block = new Block();
     private final int ARRAYROW = 25; 
     private final int ARRAYCOL = 15;
     int[][] blockArr = new int[ARRAYROW][ARRAYCOL]; 
     int[][] shapeArr = new int[ARRAYROW][ARRAYCOL]; 
     int[][] stateArr = new int[ARRAYROW][ARRAYCOL]; 
     int[][] displayArr = new int[ARRAYROW][ARRAYCOL]; 

	public static void main(String[] args) {
		new JFrmTetrisWarmUpVer1();			
	}
 
	public JFrmTetrisWarmUpVer1(){		
		setBlockArr();		
		printArray(blockArr);//�����Ͱ� ���������� �Է���� Ȯ��
		
		setBounds(300, 100, 300,500);
		//setSize(500,600);
		setVisible(true);
		
	    dim = getSize();
        addKeyListener(this); 
        setBackground(Color.black);
        //System.out.println(dim.width + " " + dim.height);
        offscreen = createImage(dim.width,dim.height);    
        bufferGraphics = offscreen.getGraphics(); 
        
        //�����带 ���� �����ð��� ���������� �Ʒ��� ���������� �Ѵ�.
//        Thread th = new Thread();
//        th.start();
        new Thread(){
        public void run() {		
    		while(true){
    			try {
    				Thread.sleep(1000);
    				if(block.i<ARRAYROW-1){
    					block.i++;
    					clearBlockArr(blockArr);
    					//clearBlockArr(displayArr);		
    					setBlockArr();
    					//printArray(blockArr);
    					//setDisplayArrFromBlockArr();
    					repaint();
    				}else{
    					break;
    				}
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		}				
    	}
        }.start();
	}

	public void clearBlockArr(int[][] data) {
		for (int i = 0; i < ARRAYROW; i++) {
			for (int j = 0; j < ARRAYCOL; j++) {
				data[i][j] = 0;
			}
		}
	}

	
	//2�����迭�� �� �ִ� �����͸� ����غ��� �޼ҵ�
	public void printArray(int[][] data){
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]);				
			}
			System.out.println("");
		}
	}
	
	
	public void paint(Graphics g) {
		System.out.println("");// clearRectȣ���ϱ� ���� ����� �ð��� ��� ���� ������.
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);

		// bufferGraphics.drawString("Bad Double-buffered",10,50);
		for (int i = 0; i < ARRAYROW; i++) {
			for (int j = 0; j < ARRAYCOL; j++) {
				if (blockArr[i][j] == 0) {
					bufferGraphics.setColor(Color.black);					
				}else if (blockArr[i][j] == 1) {
					bufferGraphics.setColor(Color.red);					
				}else if (blockArr[i][j] == 2) {
					bufferGraphics.setColor(Color.gray);					
				}else if (blockArr[i][j] == 3) {
					bufferGraphics.setColor(Color.green);					
				}else if (blockArr[i][j] == 4) {
					bufferGraphics.setColor(Color.blue);					
				}else if (blockArr[i][j] == 5) {
					bufferGraphics.setColor(Color.cyan);					
				}else if (blockArr[i][j] == 6) {
					bufferGraphics.setColor(Color.darkGray);					
				}else if (blockArr[i][j] == 7) {
					bufferGraphics.setColor(Color.orange);					
				}
				
				bufferGraphics.fillRect((j * 20), (i * 20), 19, 19);
				// ���� �����̿� ������ ����� ���� 19�� ���
			}
		}

		g.drawImage(offscreen, 0, 0, this);
	}

     public void update(Graphics g) 
     { 
          paint(g); 
     }

	@Override
	public void keyPressed(KeyEvent e) {
		
		//System.out.println(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (block.shape==1||block.shape==2||block.shape==3) {
				block.state=(block.state+1)%2;
			}
			if (block.shape==4||block.shape==5||block.shape==6) {
				block.state++;
				block.state=block.state%4;
			}
			break;
		case KeyEvent.VK_DOWN:
			block.i++;			
			break;
		case KeyEvent.VK_LEFT:
			block.j--;		
			break;
		case KeyEvent.VK_RIGHT:
			block.j++;		
			break;
		case KeyEvent.VK_SPACE:
			
			break;

		default:
			break;
		}
		clearBlockArr(blockArr);
		//clearBlockArr(displayArr);		
		setBlockArr();
		//printArray(blockArr);
		//setDisplayArrFromBlockArr();
		repaint();
	}

	public void setDisplayArrFromBlockArr() {
		for (int i = 0; i < ARRAYROW; i++) {
			for (int j = 0; j < ARRAYCOL; j++) {
				if (blockArr[i][j] != 0) {
					displayArr[i][j] = blockArr[i][j];
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	} 
	
	public void setBlockArr(){
		int i = block.i;
		int j = block.j;
		
		int shape = block.shape;
		int state = block.state;
		//System.out.println("shape : " +shape);
		if(shape==0){//�׸� ����			
			blockArr[i][j] = shape+1;
			blockArr[i][j+1] = shape+1;
			blockArr[i+1][j] = shape+1;
			blockArr[i+1][j+1] = shape+1;			
		}
		
		if(shape==1){//������ ����
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j-2] = shape+1;
				blockArr[i][j+1] = shape+1;
			}else if (state==1) {
				blockArr[i-2][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i][j] = shape+1;
				blockArr[i+1][j] = shape+1;
			}			
		}
		
		if(shape==2){//'��'�� ����
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i-1][j-1] = shape+1;
			}else if (state==1) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i-1][j+1] = shape+1;
			}			
		}
		
		if(shape==3){//'��'�� ��Ī ���� 
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i-1][j+1] = shape+1;
			}else if (state==1) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i+1][j+1] = shape+1;
			}			
		}
		
		if(shape==4){//'�� ' ����
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i-1][j] = shape+1;
			}else if (state==1) {
				blockArr[i][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i][j+1] = shape+1;
			}else if (state==2){
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i+1][j] = shape+1;
			}else if (state==3){
				blockArr[i][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i][j-1] = shape+1;
			}			
		}
		
		if(shape==5){//'��' ����
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i-1][j-1] = shape+1;
			}else if (state==1) {
				blockArr[i][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i-1][j+1] = shape+1;
			}else if (state==2){
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i+1][j+1] = shape+1;
			}else if (state==3){
				blockArr[i][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i+1][j-1] = shape+1;
				blockArr[i-1][j] = shape+1;
			}			
		}
		
		if(shape==6){//'��'��Ī����
			if (state==0) {
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i-1][j+1] = shape+1;
			}else if (state==1) {
				blockArr[i][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i+1][j+1] = shape+1;
			}else if (state==2){
				blockArr[i][j] = shape+1;
				blockArr[i][j-1] = shape+1;
				blockArr[i][j+1] = shape+1;
				blockArr[i+1][j-1] = shape+1;
			}else if (state==3){
				blockArr[i][j] = shape+1;
				blockArr[i-1][j] = shape+1;
				blockArr[i+1][j] = shape+1;
				blockArr[i-1][j-1] = shape+1;
			}			
		}		
	}
	
	
	class Block {//��ϸ����� ��Ÿ���� Ŭ����
		public int i = 2;
		public int j = 4;
		public int shape = (int)(Math.random()*6+1);		
		public int state = 0;
	}	
 } 

