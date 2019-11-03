/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class Aokizi extends AbilityBase
/*     */ {
/*     */   public Aokizi()
/*     */   {
/*  23 */     InitAbility("아오키지", AbilityBase.Type.Active_Immediately, 
/*  24 */       AbilityBase.Rank.S, new String[] {
/*  25 */       "철괴로 왼쪽클릭시 자신이 보고있는 방향으로 얼음을 날립니다.", 
/*  26 */       "철괴로 오른쪽클릭시 자신이 바라보고 있는 5칸 이내의 물을 얼려버립니다." });
/*  27 */     InitAbility(1, 0, true, AbilityBase.ShowText.Custom_Text);
/*  28 */     RegisterLeftClickEvent();
/*  29 */     RegisterRightClickEvent();
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  33 */     if (CustomData == 0) {
/*  34 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  35 */       if ((PlayerCheck(Event.getPlayer())) && 
/*  36 */         (ItemCheck(Material.IRON_INGOT.getId())) && !EventManager.DamageGuard)
/*  37 */         return 0;
/*  38 */     } else if (CustomData == 1) {
/*  39 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  40 */       if ((PlayerCheck(Event.getPlayer())) && 
/*  41 */         (ItemCheck(Material.IRON_INGOT.getId())) && !EventManager.DamageGuard) {
/*  42 */         Player p = Event.getPlayer();
/*  43 */         Block block = p.getTargetBlock(null, 0);
/*  44 */         if ((block.getTypeId() == 8) || (block.getTypeId() == 9)) {
/*  45 */           if (p.getLocation().distance(block.getLocation()) <= 5.0D) {
/*  46 */             block.setType(Material.ICE);
/*     */           } else {
/*  48 */             GetPlayer().sendMessage(
/*  49 */               org.bukkit.ChatColor.GREEN + 
/*  50 */               "너무 멉니다 [5칸이내의 물만 얼릴 수 있습니다.]");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  55 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/*  59 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  60 */     Location l = Event.getPlayer().getLocation();
/*  61 */     Location l2 = Event.getPlayer().getLocation();
/*  62 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/*  63 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/*  64 */     Timer timer = new Timer();
/*  65 */     for (int i = 1; i < 10; i++) {
/*  66 */       l2.setX(l.getX() + (1 * i + 1) * (
/*  67 */         Math.sin(degrees) * Math.cos(ydeg)));
/*  68 */       l2.setY(l.getY() + (1 * i + 1) * Math.sin(ydeg));
/*  69 */       l2.setZ(l.getZ() + (1 * i + 1) * (
/*  70 */         Math.cos(degrees) * Math.cos(ydeg)));
/*  71 */       if (l2.getWorld().getBlockAt(l2).getType() != Material.ICE)
/*  72 */         timer.schedule(new ExplosionTimer2(l2.getWorld().getBlockAt(l2)
/*  73 */           .getTypeId(), l2.getWorld().getBlockAt(l2)), 988L);
/*  74 */       l2.getWorld().getBlockAt(l2).setType(Material.ICE);
/*  75 */       Player[] List = Bukkit.getOnlinePlayers();
/*  76 */       Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = List).length; for (int k = 0; k < j; k++) { Player p = arrayOfPlayer1[k];
/*  77 */         if (p != GetPlayer()) {
/*  78 */           Location loc = p.getLocation();
/*     */           
/*     */ 
/*  81 */           if (l2.getWorld().getBlockAt(l2).getLocation().distance(loc) <= 3.0D)
/*  82 */             p.damage((int) 7.0D, GetPlayer());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class ExplosionTimer extends TimerTask {
/*     */     World world;
/*     */     Location location;
/*     */     Location location2;
/*     */     
/*  93 */     ExplosionTimer(int blockid, Block block) { this.world = block.getWorld();
/*  94 */       this.location = block.getLocation();
/*  95 */       this.location2 = block.getLocation();
/*     */     }
/*     */     
/*     */     public void run() {
/*  99 */       this.world.getBlockAt(this.location).breakNaturally();
/*     */     }
/*     */   }
/*     */   
/*     */   class ExplosionTimer2 extends TimerTask {
/*     */     World world;
/*     */     Location location;
/*     */     Location location2;
/*     */     private int blockd;
/*     */     
/*     */     ExplosionTimer2(int blockid, Block block) {
/* 110 */       this.world = block.getWorld();
/* 111 */       this.location = block.getLocation();
/* 112 */       this.location2 = block.getLocation();
/* 113 */       this.blockd = blockid;
/*     */     }
/*     */     
/*     */     public void run() {
/* 117 */       this.world.getBlockAt(this.location).setTypeId(this.blockd);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Aokizi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */