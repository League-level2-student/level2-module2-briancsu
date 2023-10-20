package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    int foodX;
    int foodY;
    Segment head;
    int foodEaten = 0;
    int direction = UP;
    ArrayList <Segment> seg = new ArrayList<Segment>();
    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(WIDTH,HEIGHT);
    }

    @Override
    public void setup() {
        head = new Segment(WIDTH/2,HEIGHT/2);
        frameRate(20);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,0);
    	drawFood();
        move();
    	drawSnake();
        checkBoundaries();
        eat();
    }

    void drawFood() {
        // Draw the food
        fill(255,0,0);
    	rect(foodX,foodY,10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,255,0);
    	rect(head.x,head.y,10,10);
    	manageTail();
    }
    void drawTail() {
        // Draw each segment of the tail
    	fill(0,255,0);
    	for(int i = 0; i < seg.size(); ++i) {
    		
    	
    	if(direction == UP) { 
    		rect(head.x, head.y+(10 * i),10,10);
    	}
    	
    	else if(direction == DOWN) {
    		rect(head.x, head.y-(10 * i),10,10);
    	}
    	else if(direction == LEFT) {
    		rect(head.x + (10 * i), head.y,10,10);
        }
    	if(direction == RIGHT) {
    		rect(head.x - (10 * i), head.y,10,10);
        }
    	}
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	
    	checkTailCollision();
    	
    	drawTail();
    	seg.add(new Segment (head.x,head.y));
    	seg.remove(0);
    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        for(int i = 0; i < seg.size(); ++i) {
        	if(head.x == seg.get(i).x && head.y == seg.get(i).y ) {
        		foodEaten = 1;
        		seg.removeAll(seg);
        		seg.add(new Segment(head.x ,head.y));
        	
        	}
        }
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
        if(keyCode == UP) {
        	direction = UP;
        }
        else if(keyCode == DOWN) {
        	direction = DOWN;
        }
        else if(keyCode == LEFT) {
        	direction = LEFT;
        }
        else if(keyCode == RIGHT) {
        	direction = RIGHT;
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
            head.y -=4;
        	// Move head up
            
        } else if (direction == DOWN) {
        	head.y +=4;
        	// Move head down
                
        } else if (direction == LEFT) {
        	head.x -=4;
        } else if (direction == RIGHT) {
        	head.x +=4;
        }
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x < 0) {
        	head.x = WIDTH;
        }
        if(head.x > WIDTH) {
        	head.x = 0;
        }
        if(head.y < 0) {
        	head.y = HEIGHT;
        }
        if(head.y > HEIGHT) {
        	head.y = 0;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(foodX >= head.x-10 && foodX <= head.x + 10 && foodY >= head.y-10 && foodY <= head.y + 10) {
        	foodEaten ++;
        	System.out.println(foodEaten);
        	dropFood();
        	drawFood();
        	seg.add(new Segment(head.x,head.y));
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
