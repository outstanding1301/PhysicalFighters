/*    */ package Physical.Fighters.OtherModule;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class Vector
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public Vector(double x, double y, double z)
/*    */   {
/* 13 */     this.x = x;
/* 14 */     this.y = y;
/* 15 */     this.z = z;
/*    */   }
/*    */   
/*    */   public Vector(Location l) {
/* 19 */     this.x = l.getX();
/* 20 */     this.y = l.getY();
/* 21 */     this.z = l.getZ();
/*    */   }
/*    */   
/*    */   public double getX() {
/* 25 */     return this.x;
/*    */   }
/*    */   
/*    */   public int getBlockX() {
/* 29 */     return (int)Math.round(this.x);
/*    */   }
/*    */   
/*    */   public Vector setX(double x) {
/* 33 */     this.x = x;
/* 34 */     return new Vector(x, this.y, this.z);
/*    */   }
/*    */   
/*    */   public Vector setX(int x) {
/* 38 */     this.x = x;
/* 39 */     return new Vector(x, this.y, this.z);
/*    */   }
/*    */   
/*    */   public double getY() {
/* 43 */     return this.y;
/*    */   }
/*    */   
/*    */   public int getBlockY() {
/* 47 */     return (int)Math.round(this.y);
/*    */   }
/*    */   
/*    */   public Vector setY(double y) {
/* 51 */     this.y = y;
/* 52 */     return new Vector(this.x, y, this.z);
/*    */   }
/*    */   
/*    */   public Vector setY(int y) {
/* 56 */     this.y = y;
/* 57 */     return new Vector(this.x, y, this.z);
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 61 */     return this.z;
/*    */   }
/*    */   
/*    */   public int getBlockZ() {
/* 65 */     return (int)Math.round(this.z);
/*    */   }
/*    */   
/*    */   public Vector setZ(double z) {
/* 69 */     this.z = z;
/* 70 */     return new Vector(this.x, this.y, z);
/*    */   }
/*    */   
/*    */   public Vector setZ(int z) {
/* 74 */     this.z = z;
/* 75 */     return new Vector(this.x, this.y, z);
/*    */   }
/*    */   
/*    */   public double distance(Vector pt) {
/* 79 */     return 
/* 80 */       Math.sqrt(Math.pow(pt.x - this.x, 2.0D) + 
/* 81 */       Math.pow(pt.y - this.y, 2.0D) + 
/* 82 */       Math.pow(pt.z - this.z, 2.0D));
/*    */   }
/*    */   
/*    */   public double distance(Location l) {
/* 86 */     return Math.sqrt(Math.pow(l.getX() - this.x, 2.0D) + 
/* 87 */       Math.pow(l.getY() - this.y, 2.0D) + 
/* 88 */       Math.pow(l.getZ() - this.z, 2.0D));
/*    */   }
/*    */   
/*    */   public double distance(double xx, double yy, double zz) {
/* 92 */     return Math.sqrt(Math.pow(xx - this.x, 2.0D) + 
/* 93 */       Math.pow(yy - this.y, 2.0D) + Math.pow(zz - this.z, 2.0D));
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\OtherModule\Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */