///////Ryan Rosario
///////Project-3

////Globals
Ball [] ros = new Ball[16];
Button[] rya = new Button [7];
Rodent mouse;
Bird pigeon;
////Table
float left, right, top, bottom;
float middle;
boolean wall=true;
int tableRed=173, tableGreen=75, tableBlue=255;
///Count
int score= 0;

boolean list= false;
boolean sort= false;

void setup() {
  size(700, 500);
   left= 50;
   right= width-50;
   top= 100;
   bottom= height-50;
   middle= right - left/2;
///Cue
  ros [0]= new Ball(0,255,255,255);
  ros [0].r=255;
  ros [0].g=255;
  ros [0].b=255;
///Balls
  for (int i=1; i<ros.length; i++){ros[i]= new Ball(i,255,255,255); } 
///Rodent
   mouse= new Rodent();
///Bird
   pigeon= new Bird();
////Buttons
///Reset   
   rya[0]= new Button();
   rya[0].g=255;
   rya[0].name= "Reset";
///Wall
   rya[1]= new Button();
   rya[1].r=255;
   rya[1].name= "Wall";
///Mouse
   rya[2]= new Button();
   rya[2].r=149;
   rya[2].g=82;
   rya[2].b=11;
   rya[2].name= "Mouse";
///Bird
   rya[3]= new Button();
   rya[3].b=255;
   rya[3].name= "Bird/bomb";
///Closest
   rya[4]= new Button();
   rya[4].g=152;
   rya[4].b=110;
   rya[4].name= "Closest";
///List
   rya[5]= new Button();
   rya[5].g=178;
   rya[5].b=111;
   rya[5].name= "List";
///Sort
   rya[6]= new Button();
   rya[6].g=116;
   rya[6].b=255;
   rya[6].name="Sort";
   
   
   //
   reset();
}

void reset(){
  ros[0].x=100; ros[0].y=280; ros[0].dx=0; ros[0].dy=0;
  for (int i=1; i<ros.length; i++){ros[i].reset();}
  mouse.reset();
  pigeon.reset();
  rya[0].reset();
  rya[1].x1=120; rya[1].x2=190; rya[1].y1=30; rya[1].y2=60;
  rya[2].x1=200; rya[2].x2=270; rya[2].y1=30; rya[2].y2=60;
  rya[3].x1=280; rya[3].x2=360; rya[3].y1=30; rya[3].y2=60;
  rya[4].x1=370; rya[4].x2=440; rya[4].y1=30; rya[4].y2=60;
  rya[5].x1=450; rya[5].x2=520; rya[5].y1=30; rya[5].y2=60;
  rya[6].x1=530; rya[6].x2=600; rya[6].y1=30; rya[6].y2=60;
///wall
   wall=true;
///color
   tableRed=173; tableGreen=75; tableBlue=255;
///count
   score= 0;
}

/////Handlers: Keys, Clicks
 void keyPressed() {
   if(key == 'q') {
      exit();
   }
   if(key == 'r') {
     reset();
   }
///Remove wall   
   if(key == 'w') {
     wall=false;
   }
///Release mouse   
   if(key == 'm') {
     mouse.dx=5;
     mouse.dy=3;
   }
///Release bird   
   if(key == 'b'&& pigeon.dx == 0) {
     pigeon.dx=3;
   }
///Drop bomb   
   if(key == 'b'&& pigeon.x > 0) {
     pigeon.payload();
 }
 }
 
 void mousePressed(){
   if(pigeon.hit(mouseX, mouseY)){ /////drop bomb
     pigeon.payload();
   }
   if(mouse.hit(mouseX, mouseY)){ ////catch mouse
     mouse.reset();
     score = score + 50;
   }
   ////balls:reset position
   if(ros[0].hit(mouseX, mouseY)){
      ros[0].x=100; ros[0].y=280; ros[0].dx=0; ros[0].dy=0;
      score -= 5;}
  for(int i=1; i<ros.length; i++){ if(ros[i].hit(mouseX,mouseY)){ ros[i].reset();}}
   ////buttons
   if(mouseX > rya[0].x1 && mouseX < rya[0].x2 && mouseY > rya[0].y1 && mouseY < rya[0].y2 ) {      //// reset table
        reset();}
   
   if(mouseX > rya[1].x1 && mouseX < rya[1].x2 && mouseY > rya[1].y1 && mouseY < rya[1].y2 ) {     //// remove wall
        wall=false;}
   
   if(mouseX > rya[2].x1 && mouseX < rya[2].x2 && mouseY > rya[2].y1 && mouseY < rya[2].y2 ) {    //// release rodent
     mouse.dx=5;
     mouse.dy=3;}       
   
   if(mouseX > rya[3].x1 && mouseX < rya[3].x2 && mouseY > rya[3].y1 && mouseY < rya[3].y2 && pigeon.dx == 0 ) {     //// release bird
       pigeon.dx=3;}  
    
   if(mouseX > rya[3].x1 && mouseX < rya[3].x2 && mouseY > rya[3].y1 && mouseY < rya[3].y2 && pigeon.x>0 ){   ////drop bomb
      pigeon.payload();}
      
   if(mouseX > rya[5].x1 && mouseX < rya[5].x2 && mouseY > rya[5].y1 && mouseY < rya[5].y2) {   /////List
     list = !list;}
   
   if(mouseX > rya[6].x1 && mouseX < rya[6].x2 && mouseY > rya[6].y1 && mouseY < rya[6].y2) {   /////List
     sort=!sort; list = !list;}
 }
    
    
   
 
 
///Functions 
 void draw(){
     background( #19F5E0 );
     rectMode( CORNERS );
     table(left, top, right, bottom);
     balls();
     button();
     rodent();
     bird();
     grass();
     info();
     listInfo( ros, ros.length);
     sortBallY( ros, ros.length);
 }
////Scene: Table and wall
 void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );
 

 if (wall) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  } else wall = false; 
   stroke(0);
   strokeWeight(1);
 }
////Show: Balls, Rodent, Bird 
 void balls() {
    if(!list) {
    for(int i=0; i<ros.length; i++){ros[i].move();}}
    for (int i = 0; i < ros.length; i++) {
    for (int j = i + 1; j < ros.length; j++) {collision(ros[i], ros[j]);}}
    //
    for (int i=0; i<ros.length; i++){ros[i].show();}
 }
 
 void button(){
  for(int i=0; i<rya.length; i++){rya[i].show();}
 
  ////buttons light up
  for(int i=0; i<rya.length; i++){
     if(mouseX > rya[i].x1 && mouseX < rya[i].x2 && mouseY > rya[i].y1 && mouseY < rya[i].y2 ){rya[i].r=255;}
     else{rya[i].r=100;}}
     
 }
   
   
 
 void rodent() {
    for (int i = 0; i < ros.length; i++) {snatch(mouse, ros[i]);}
    //
    mouse.show();
    mouse.move();
 }
 void bird() {
    for (int i = 0; i < ros.length; i++) {kablooey(pigeon, ros[i]);}
    //
    pigeon.show();
    pigeon.move();
 }
   
////Elastic collisions, Rodent collision, Bomb collision 
 void collision(Ball p, Ball q) {
   if(p.hit( q.x, q.y) ) {
     float tmp;
     tmp=p.dx; p.dx=q.dx; q.dx=tmp;
     tmp=p.dy; p.dy=q.dy; q.dy=tmp;
     {p.x += p.dx;}  
     {p.y += p.dy;}

     score += 1;
   }
  }
  
  void snatch(Rodent mouse, Ball t) {
    if(mouse.hit(t.x, t.y)) {
      t.dx=0; t.dy=0; 
      score -= 10;
    }
 }
  
  void kablooey(Bird pigeon, Ball u){
     if(dist(pigeon.x, pigeon.bombY, u.x, u.y)<30) {
       pigeon.bombY=0; pigeon.bombDY=0; 
       score += 10;
     }
  }

////Loops: Grass    
  void grass(){
   stroke(#469B16); 
   strokeWeight(5);
   ////grass
int grassy=475;
int grassx=1;
int spacing=6;
int len=150;
int endLegs=699;
   while(grassx <= endLegs) {
     line(grassx,grassy,grassx,grassy+len);
     grassx=grassx+spacing;
   }
  }
  
 void listInfo( Ball[] a, int many) {
   if (list) {
 float x= width/2;
 float y= 100; 
  text("num", x+65, y);
  text( "x", x+115, y );
  text( "y", x+165, y );
  fill(0);
  //
 for(int i=0; i<many; i++){
  y += 15;
  text( i, x+60, y);
  text( a[i].x, x+100, y );
  text( a[i].y, x+165, y );
 }
}
  else{ list = false;}
 }
 
 void sortBallY( Ball[] a, int many) {
   if(sort) {
   for(int m=many; m>1; m--) {
     int k=0;
   for(int j=1; j<m; j++) {
    if( a[j].y> a[k].y) k=j;
   }
    swapY(a, m-1, k);
   }
   }
   else{ sort = false;}
 }
 
 void swapY(Ball[] a, int j, int k) {
    Ball t;
    t=a[k];
    a[k]= a[j];
    a[j]= t;
 }
     
   
   
     
///Messages   
  void info(){
  fill(0);     text(score, 650, 50);
  fill(0);     text("Ryan Rosario", 50, height-30);
  fill(0);     text("CST-112: Project 3", width/2.5, 10);
  fill(0);     text("click any ball to reset position (-5 points). Press q to exit", width/4, 20);
  
  
  }
  
 
 
 


/////Classes  
 class Ball {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Ball "has".)
  float x,y, dx,dy;
  int r,g,b;
  int num;
  //String name= "";
  
  Ball (int a, int r, int g, int b){
    num=a;
    this.r=r;
    this.g=g;
    this.b=b;
    randomize();
  }
  
  void randomize(){
   r=int(random(0,255));
   g=int(random(0,255));
   b=int(random(0,255));
  }
  
  
 //// METHODS
  void show() {
    fill(r,g,b);
    ellipse( x,y, 30,30 );
    fill(0);
    text( num, x-5,y );
  }
  void move() {
    if(wall) {
    if (x>right || x<width/1.9) {  dx=  -dx; }
    if (y>bottom || y<top) {  dy=  -dy; }
    }
    else {
    if (x>right || x<left) {  dx=  -dx; }
    if (y>bottom || y<top) {  dy=  -dy; }
    }

    
    x += dx;
    y += dy;
  }
  void reset() {
    x=  random(middle, right);
    y=  random( top, bottom );
    dx=  random( 1,3);
    dy=  random( 1,3 );
  }

  
  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
  }
}

class Button {
  float x1, y1, x2, y2;
  int r,g,b;
  String name= "";
 
////Methods
  void show(){     
  fill(r,g,b);
  rect(x1,y1,x2,y2);
  fill(0); text(name, x1+10, y1+20);
  }
  
  void reset(){
  x1=40; x2=110;
  y1=30; y2=60;
}
}
   

class Rodent{
////Properties   
  float x, y, dx, dy;

////Methods
  void show(){
    fill(#AA5D29);                     ellipse(x,y, 60, 30);//// Rodent body
    fill(#FF55F7);                     ellipse(x+20, y-15, 10,20);//// Rodent left ear
    fill(#FF55F7);                     ellipse(x+40, y-15, 10,20);//// Rodent right ear
    fill( #AA5D29);                    ellipse(x+30, y-10, 40,20); ///// Rodent head
    fill(#FF55F7);                     ellipse(x+50, y-10, 5,5);   ///// Rodent nose
    fill(0);                           ellipse(x+35, y-15, 5,5);  ///// Rodent eye
    stroke(#FF55F7); strokeWeight(2);  line(x-30, y, x-50, y+30); ////Rodent tail
    stroke(0);///color Rodent legs black
//////Rodent animation: leg movement
    if(frameCount % 30 > 15){ 
///Rodent legs first position
    line( x-20, y+10, x-30, y+20);
    line(x+20, y+10, x+30, y+20);}
///Rodent leg second position
    else{line( x-10, y+10, x-10, y+20);
   }
  }
 
 void move(){
///mouse remains on table
   if (y>bottom || y<top) {  dy=  -dy; }
///reset mouse position without resetting entire program   
   if(x>width) {reset();}
   x+=dx;
   y+=dy;
 
   
 }
    

  void reset(){
///mouse is not a nusance until button is pressed
    x=width-790; y=random(top,bottom);
    dx=0; dy=0;
  }
  
  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
 }
}

class Bird {
////Properties
  float x, y, dx, dy;
  float bombY=0, bombDY=0;
  float grav=9.81/60;

////Methods
  void show() {
    fill(#7C95AF);     ellipse(x,y, 40, 20);///body
    if(dx == -3 ){
    fill(#7C95AF);     ellipse(x-20,y-10, 20, 20); }////head facing right
    else{
    fill(#7C95AF);     ellipse(x+20,y-10, 20,20);}///head facing left
   ///wings   
   if(frameCount % 30 > 15){ 
    fill(#1665B7); triangle(x-10,y, x, y+25,  x+10, y);
   }
   else{fill(#1665B7); triangle(x-10, y, x, y-25, x+10, y);
   }
   ///bomb
   if(bombDY != 0){
    fill(#B26C16); ellipse(x-2, bombY-25, 3, 25);///fuse;
    fill(#202236); ellipse(x,bombY, 30,30);///case
  }
}

  void move() {
   ///bird
   if( x>width) { dx= -dx;}
   if(dx == -3 && x<0) {dx= -dx;} 
     x+=dx;
   ///bomb
   if(bombDY != 0){ 
       bombY += bombDY;
       bombDY+=grav;
   }
   if(bombY>height){
     bombY=0;
     bombDY=0;
   }
  }
  
  void payload(){
    bombY=y+10;
    bombDY= grav;
    
  }
 
 
  void reset() {
    x = width-740; y=35;
    dx=0;
    bombDY=0;
  }
 boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
 }
}

 
   
       
    


  
 
   
   





