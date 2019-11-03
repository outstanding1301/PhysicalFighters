/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.PhysicalFighters;
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
/*     */ public class Apollon
/*     */   extends AbilityBase
/*     */ {
/*     */   public Apollon()
/*     */   {
/*  24 */     if ((!PhysicalFighters.Toner) && 
/*  25 */       (PhysicalFighters.SRankUsed)) {
/*  26 */       InitAbility("아폴론", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/*  27 */         "바라보는 방향에 불구덩이를 만듭니다." });
/*  28 */       InitAbility(40, 0, true);
/*  29 */       RegisterLeftClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  34 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  35 */     if ((!EventManager.DamageGuard) && 
/*  36 */       (PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/*  37 */       return 0;
/*     */     }
/*  39 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  44 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  45 */     Player p = Event.getPlayer();
/*  46 */     Location l1 = p.getTargetBlock(null, 0).getLocation();
/*  47 */     Location l2 = p.getTargetBlock(null, 0).getLocation();
/*  48 */     for (int j = 0; j <= 7; j++) {
/*  49 */       l2.setY(l1.getY() - j);
/*  50 */       for (int i = 0; i <= 4; i++)
/*     */       {
/*  52 */         l2.setX(l1.getX() + i);
/*  53 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*  54 */         for (int k = 0; k <= 4; k++) {
/*  55 */           l2.setZ(l1.getZ() + k);
/*  56 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/*  59 */       for (int i = 0; i <= 4; i++)
/*     */       {
/*  61 */         l2.setX(l1.getX() - i);
/*  62 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*  63 */         for (int k = 0; k <= 4; k++) {
/*  64 */           l2.setZ(l1.getZ() - k);
/*  65 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/*  68 */       for (int i = 0; i <= 4; i++)
/*     */       {
/*  70 */         l2.setX(l1.getX() - i);
/*     */         
/*  72 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*  73 */         for (int k = 0; k <= 4; k++) {
/*  74 */           l2.setZ(l1.getZ() + k);
/*     */           
/*  76 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/*  79 */       for (int i = 0; i <= 4; i++)
/*     */       {
/*  81 */         l2.setX(l1.getX() + i);
/*     */         
/*  83 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*  84 */         for (int k = 0; k <= 4; k++) {
/*  85 */           l2.setZ(l1.getZ() - k);
/*     */           
/*  87 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/*     */     }
/*  91 */     for (int j = 0; j <= 6; j++) {
/*  92 */       l2.setY(l1.getY() - j);
/*  93 */       for (int i = 0; i <= 3; i++)
/*     */       {
/*  95 */         l2.setX(l1.getX() + i);
/*     */         
/*  97 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*  98 */         for (int k = 0; k <= 3; k++) {
/*  99 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 101 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 104 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 106 */         l2.setX(l1.getX() - i);
/*     */         
/* 108 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 109 */         for (int k = 0; k <= 3; k++) {
/* 110 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 112 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 115 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 117 */         l2.setX(l1.getX() - i);
/*     */         
/* 119 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 120 */         for (int k = 0; k <= 3; k++) {
/* 121 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 123 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 126 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 128 */         l2.setX(l1.getX() + i);
/*     */         
/* 130 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 131 */         for (int k = 0; k <= 3; k++) {
/* 132 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 134 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/* 138 */     for (int j = 6; j <= 6; j++) {
/* 139 */       l2.setY(l1.getY() - j);
/* 140 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 142 */         l2.setX(l1.getX() + i);
/*     */         
/* 144 */         p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/* 145 */         for (int k = 0; k <= 3; k++) {
/* 146 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 148 */           p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/*     */         }
/*     */       }
/* 151 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 153 */         l2.setX(l1.getX() - i);
/*     */         
/* 155 */         p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/* 156 */         for (int k = 0; k <= 3; k++) {
/* 157 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 159 */           p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/*     */         }
/*     */       }
/* 162 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 164 */         l2.setX(l1.getX() - i);
/*     */         
/* 166 */         p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/* 167 */         for (int k = 0; k <= 3; k++) {
/* 168 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 170 */           p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/*     */         }
/*     */       }
/* 173 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 175 */         l2.setX(l1.getX() + i);
/*     */         
/* 177 */         p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/* 178 */         for (int k = 0; k <= 3; k++) {
/* 179 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 181 */           p.getWorld().getBlockAt(l2).setType(Material.FIRE);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Apollon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */