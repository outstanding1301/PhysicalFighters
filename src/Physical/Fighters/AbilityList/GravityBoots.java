/*     */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class GravityBoots extends AbilityBase
/*     */ {
/*  25 */   Block b = null;
/*     */   int bt;
/*     */   
/*     */   public GravityBoots() {
/*  29 */     InitAbility("중력장화", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  30 */       "아무것도 신지않고, 철괴로 왼쪽클릭을 하면, 바라보는 블럭에 10초간 청금석이 생깁니다.", 
/*  31 */       "능력사용자를 제외한 청금석 주변의 플레이어는 지속 데미지와 함께 청금석으로 끌려옵니다.", 
/*  32 */       "아무것도 신지않고있는 경우 낙하데미지를 받지 않습니다." });
/*  33 */     InitAbility(40, 10, true);
/*  34 */     RegisterLeftClickEvent();
/*  35 */     EventManager.onEntityDamage.add(new EventData(this, 1));
/*  36 */     EventManager.onBlockBreakEvent.add(new EventData(this, 2));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  41 */     switch (CustomData) {
/*     */     case 0: 
/*  43 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/*  44 */       if ((PlayerCheck(e.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/*  45 */         Player p = e.getPlayer();
/*  46 */         if (p.getInventory().getBoots() == null)
/*     */         {
/*  48 */           if (p.getLocation().distance(p.getTargetBlock(null, 0).getLocation()) < 30.0D) {
/*  49 */             return 0;
/*     */           }
/*  51 */           p.sendMessage(ChatColor.RED + "너무 멉니다.");
/*     */         }
/*     */       }
/*  54 */       break;
/*     */     case 1: 
/*  56 */       EntityDamageEvent e2 = (EntityDamageEvent)event;
/*  57 */       if (PlayerCheck(e2.getEntity())) {
/*  58 */         Player p = (Player)e2.getEntity();
/*  59 */         if ((p.getInventory().getBoots() == null) && 
/*  60 */           (e2.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL)) {
/*  61 */           e2.setCancelled(true);
/*     */         }
/*     */       }
/*  64 */       break;
/*     */     case 2: 
/*  66 */       BlockBreakEvent e3 = (BlockBreakEvent)event;
/*  67 */       if (e3.getBlock() == this.b) {
/*  68 */         e3.setCancelled(true);
/*     */       }
/*     */       break;
/*     */     }
/*  72 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  77 */     PlayerInteractEvent e = (PlayerInteractEvent)event;
/*  78 */     Player p = e.getPlayer();
/*  79 */     Block tb = p.getTargetBlock(null, 0);
/*  80 */     this.bt = tb.getTypeId();
/*  81 */     tb.setType(Material.LAPIS_BLOCK);
/*  82 */     this.b = tb;
/*  83 */     Timer timer = new Timer();
/*  84 */     timer.schedule(new GTimer(), 1000L, 1000L);
/*     */   }
/*     */   
/*     */   public class GTimer extends TimerTask {
/*  88 */     private int a = 10;
/*     */     
/*     */     public GTimer() {}
/*     */     
/*  92 */     public void run() { Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player p = arrayOfPlayer1[i];
/*  93 */         if ((GravityBoots.this.b.getLocation().distance(p.getLocation()) < 10.0D) && (p != GravityBoots.this.GetPlayer())) {
/*  94 */           p.teleport(GravityBoots.this.b.getLocation());
/*  95 */           p.damage(3, GravityBoots.this.GetPlayer());
/*     */         }
/*     */       }
/*  98 */       this.a -= 1;
/*  99 */       if (this.a <= 0) {
/* 100 */         Location l = GravityBoots.this.b.getLocation();
/* 101 */         l.setY(GravityBoots.this.b.getLocation().getY() + 1.0D);
/* 102 */         Player[] arrayOfPlayer2; int k = (arrayOfPlayer2 = Bukkit.getOnlinePlayers()).length; for (j = 0; j < k; j++) { Player p = arrayOfPlayer2[j];
/* 103 */           if ((GravityBoots.this.b.getLocation().distance(p.getLocation()) < 10.0D) && (p != GravityBoots.this.GetPlayer())) {
/* 104 */             p.teleport(l);
/* 105 */             p.damage(5, GravityBoots.this.GetPlayer());
/*     */           }
/*     */         }
/* 108 */         GravityBoots.this.b.setTypeId(GravityBoots.this.bt);
/* 109 */         GravityBoots.this.b = null;
/* 110 */         GravityBoots.this.bt = 0;
/* 111 */         cancel();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\GravityBoots.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */