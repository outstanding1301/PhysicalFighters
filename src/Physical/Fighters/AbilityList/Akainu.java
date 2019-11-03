/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class Akainu
/*     */   extends AbilityBase
/*     */ {
/*  26 */   public static Block[][][] B = new Block[5][5][3];
/*  27 */   public static Material[][][] M = new Material[5][5][3];
/*     */   
/*  29 */   public Akainu() { if ((!PhysicalFighters.Toner) && 
/*  30 */       (PhysicalFighters.SRankUsed)) {
/*  31 */       InitAbility("아카이누", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/*  32 */         "바라보는 곳의 땅을 3초동안 용암으로 바꿔버립니다.", "3초 뒤에 용암이 굳으며 땅속에 갇힙니다.", "용암속에서 데미지를 받지 않습니다." });
/*  33 */       InitAbility(30, 0, true);
/*  34 */       RegisterLeftClickEvent();
/*  35 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  41 */     if (CustomData == 0) {
/*  42 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  43 */       if ((!EventManager.DamageGuard) && 
/*  44 */         (PlayerCheck(Event.getPlayer())) && 
/*  45 */         (ItemCheck(ACC.DefaultItem)) && 
/*  46 */         (Event.getPlayer().getTargetBlock(null, 0).getType() != Material.BEDROCK)) {
/*  47 */         return 0;
/*     */       }
/*     */     }
/*  50 */     if (CustomData == 3) {
/*  51 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/*  52 */       if ((PlayerCheck(Event2.getEntity())) && (
/*  53 */         (Event2.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
/*  54 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
/*  55 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK))) {
/*  56 */         Event2.setCancelled(true);
/*     */       }
/*     */     }
/*     */     
/*  60 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  65 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  66 */     Player p = Event.getPlayer();
/*  67 */     World w = Event.getPlayer().getWorld();
/*  68 */     Location loc = p.getTargetBlock(null, 0).getLocation();
/*  69 */     Timer timer = new Timer();
/*  70 */     for (int i = -2; i < 3; i++) {
/*  71 */       for (int j = -2; j < 3; j++) {
/*  72 */         for (int k = -2; k < 1; k++) {
/*  73 */           B[(i + 2)][(j + 2)][(k + 2)] = w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j);
/*  74 */           M[(i + 2)][(j + 2)][(k + 2)] = w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j).getType();
/*  75 */           w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j).setType(Material.GRASS);
/*     */         }
/*     */       }
/*     */     }
/*  79 */     for (int i = -1; i < 2; i++) {
/*  80 */       for (int j = -1; j < 2; j++) {
/*  81 */         for (int k = -1; k < 1; k++) {
/*  82 */           w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j).setType(Material.LAVA);
/*     */         }
/*     */       }
/*     */     }
/*  86 */     timer.schedule(new ExplosionTimer(w), 3000L);
/*     */   }
/*     */   
/*     */   class ExplosionTimer extends TimerTask {
/*     */     private World w;
/*     */     
/*  92 */     public ExplosionTimer(World w1) { this.w = w1; }
/*     */     
/*     */ 
/*     */     public void run()
/*     */     {
/*  97 */       for (int i = -2; i < 3; i++) {
/*  98 */         for (int j = -2; j < 3; j++) {
/*  99 */           for (int k = -2; k < 1; k++) {
/* 100 */             Location loc = Akainu.B[(i + 2)][(j + 2)][(k + 2)].getLocation();
/* 101 */             this.w.getBlockAt(loc).setType(Akainu.M[(i + 2)][(j + 2)][(k + 2)]);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Akainu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */