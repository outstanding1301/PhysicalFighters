/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.OtherModule.Vector;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ 
/*     */ public class Gaara
/*     */   extends AbilityBase
/*     */ {
/*     */   public Gaara()
/*     */   {
/*  27 */     if (!PhysicalFighters.Toner) {
/*  28 */       InitAbility("가아라", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/*  29 */         "능력을 사용하면 모래를 떨어뜨리고 폭발시킨다." });
/*  30 */       InitAbility(30, 0, true);
/*  31 */       RegisterLeftClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  37 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  38 */     Player p = Event.getPlayer();
/*  39 */     Block b = p.getTargetBlock(null, 0);
/*  40 */     Location loc = b.getLocation();
/*  41 */     Location ploc = p.getLocation();
/*     */     
/*  45 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/*  46 */       if ((loc.distance(ploc) <= 40.0D) && (b.getY() != 0)) {
/*  47 */         if (!EventManager.DamageGuard)
/*  48 */           return 0;
/*     */       } else
/*  50 */         p.sendMessage(String.format(ChatColor.RED + "거리가 너무 멉니다.", new Object[0]));
/*     */     }
/*  52 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  57 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  58 */     Player p = Event.getPlayer();
/*  59 */     Location l1 = p.getTargetBlock(null, 0).getLocation();
/*  60 */     Location l2 = p.getTargetBlock(null, 0).getLocation();
/*  61 */     Timer timer = new Timer();
/*  62 */     Block block = Event.getPlayer().getTargetBlock(null, 0);
/*  63 */     timer.schedule(new ExplosionTimer(block), 4000L);
/*  64 */     for (int j = 4; j <= 8; j++) {
/*  65 */       l2.setY(l1.getY() + j);
/*  66 */       for (int i = 0; i <= 3; i++)
/*     */       {
/*  68 */         l2.setX(l1.getX() + i);
/*  69 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  70 */           p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*  71 */         for (int k = 0; k <= 3; k++) {
/*  72 */           l2.setZ(l1.getZ() + k);
/*  73 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  74 */             p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*     */         }
/*     */       }
/*  77 */       for (int i = 0; i <= 3; i++)
/*     */       {
/*  79 */         l2.setX(l1.getX() - i);
/*  80 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  81 */           p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*  82 */         for (int k = 0; k <= 3; k++) {
/*  83 */           l2.setZ(l1.getZ() - k);
/*  84 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  85 */             p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*     */         }
/*     */       }
/*  88 */       for (int i = 0; i <= 3; i++)
/*     */       {
/*  90 */         l2.setX(l1.getX() - i);
/*  91 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  92 */           p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*  93 */         for (int k = 0; k <= 3; k++) {
/*  94 */           l2.setZ(l1.getZ() + k);
/*  95 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  96 */             p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*     */         }
/*     */       }
/*  99 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 101 */         l2.setX(l1.getX() + i);
/* 102 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 103 */           p.getWorld().getBlockAt(l2).setType(Material.SAND);
/* 104 */         for (int k = 0; k <= 3; k++) {
/* 105 */           l2.setZ(l1.getZ() - k);
/* 106 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 107 */             p.getWorld().getBlockAt(l2).setType(Material.SAND);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class ExplosionTimer extends TimerTask {
/*     */     World world;
/*     */     Location location;
/*     */     
/* 117 */     ExplosionTimer(Block block) { this.world = block.getWorld();
/* 118 */       this.location = block.getLocation();
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 124 */       this.world.createExplosion(this.location, 5.0F);
/* 125 */       this.world.createExplosion(this.location, 5.0F);
/* 126 */       this.world.createExplosion(this.location, 5.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Gaara.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */