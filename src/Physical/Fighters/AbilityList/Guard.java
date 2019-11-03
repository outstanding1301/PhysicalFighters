/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.OtherModule.Vector;
/*     */ import Physical.Fighters.PhysicalFighters;
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
/*     */ 
/*     */ public class Guard
/*     */   extends AbilityBase
/*     */ {
/*     */   public Guard()
/*     */   {
/*  26 */     if (!PhysicalFighters.Toner) {
/*  27 */       InitAbility("목둔", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/*  28 */         "능력을 사용하면 나무 벽을 설치해 상대를 가둘 수 있다." });
/*  29 */       InitAbility(30, 0, true);
/*  30 */       RegisterLeftClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  36 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  37 */     Player p = Event.getPlayer();
/*  38 */     Block b = p.getTargetBlock(null, 0);
/*  39 */     Location loc = b.getLocation();
/*  40 */     Location ploc = p.getLocation();
/*     */     
/*  42 */     Vector playervec = new Vector(ploc.getX(), ploc.getY(), ploc.getZ());
/*  43 */     Vector targetvec = new Vector(loc.getX(), loc.getY(), loc.getZ());
/*  44 */     if ((!EventManager.DamageGuard) && 
/*  45 */       (PlayerCheck(Event.getPlayer())) && 
/*  46 */       (ItemCheck(ACC.DefaultItem)) && 
/*  47 */       (Event.getPlayer().getTargetBlock(null, 0).getType() != Material.BEDROCK)) {
/*  48 */       if ((playervec.distance(targetvec) <= 40.0D) && (b.getY() != 0)) {
/*  49 */         return 0;
/*     */       }
/*  51 */       p.sendMessage(String.format(ChatColor.RED + "거리가 너무 멉니다.", new Object[0]));
/*     */     }
/*  53 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  58 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  59 */     Player p = Event.getPlayer();
/*  60 */     Location l1 = p.getTargetBlock(null, 0).getLocation();
/*  61 */     Location l2 = p.getTargetBlock(null, 0).getLocation();
/*  62 */     for (int j = 0; j <= 8; j++) {
/*  63 */       l2.setY(l1.getY() + j);
/*  64 */       for (int i = 0; i <= 5; i++) {
/*  65 */         l2.setX(l1.getX() + i);
/*  66 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  67 */           p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*  68 */         for (int k = 0; k <= 5; k++) {
/*  69 */           l2.setZ(l1.getZ() + k);
/*  70 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  71 */             p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*     */         }
/*     */       }
/*  74 */       for (int i = 0; i <= 5; i++) {
/*  75 */         l2.setX(l1.getX() - i);
/*  76 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  77 */           p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*  78 */         for (int k = 0; k <= 5; k++) {
/*  79 */           l2.setZ(l1.getZ() - k);
/*  80 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  81 */             p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*     */         }
/*     */       }
/*  84 */       for (int i = 0; i <= 5; i++) {
/*  85 */         l2.setX(l1.getX() - i);
/*  86 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  87 */           p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*  88 */         for (int k = 0; k <= 5; k++) {
/*  89 */           l2.setZ(l1.getZ() + k);
/*  90 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  91 */             p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*     */         }
/*     */       }
/*  94 */       for (int i = 0; i <= 5; i++) {
/*  95 */         l2.setX(l1.getX() + i);
/*  96 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/*  97 */           p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*  98 */         for (int k = 0; k <= 5; k++) {
/*  99 */           l2.setZ(l1.getZ() - k);
/* 100 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 101 */             p.getWorld().getBlockAt(l2).setType(Material.WOOD);
/*     */         }
/*     */       }
/*     */     }
/* 105 */     for (int j = 1; j <= 6; j++) {
/* 106 */       l2.setY(l1.getY() + j);
/* 107 */       for (int i = 0; i <= 3; i++) {
/* 108 */         l2.setX(l1.getX() + i);
/* 109 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 110 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 111 */         for (int k = 0; k <= 3; k++) {
/* 112 */           l2.setZ(l1.getZ() + k);
/* 113 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 114 */             p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 117 */       for (int i = 0; i <= 3; i++) {
/* 118 */         l2.setX(l1.getX() - i);
/* 119 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 120 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 121 */         for (int k = 0; k <= 3; k++) {
/* 122 */           l2.setZ(l1.getZ() - k);
/* 123 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 124 */             p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 127 */       for (int i = 0; i <= 3; i++) {
/* 128 */         l2.setX(l1.getX() - i);
/* 129 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 130 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 131 */         for (int k = 0; k <= 3; k++) {
/* 132 */           l2.setZ(l1.getZ() + k);
/* 133 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 134 */             p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 137 */       for (int i = 0; i <= 3; i++) {
/* 138 */         l2.setX(l1.getX() + i);
/* 139 */         if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK)
/* 140 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 141 */         for (int k = 0; k <= 3; k++) {
/* 142 */           l2.setZ(l1.getZ() - k);
/* 143 */           if (p.getWorld().getBlockAt(l2).getType() != Material.BEDROCK) {
/* 144 */             p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Guard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */