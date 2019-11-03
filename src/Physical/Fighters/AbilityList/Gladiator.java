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
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Gladiator extends AbilityBase
/*     */ {
/*     */   public Gladiator()
/*     */   {
/*  27 */     if ((!PhysicalFighters.Toner) && 
/*  28 */       (PhysicalFighters.SRankUsed)) {
/*  29 */       InitAbility("글레디에이터", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SSS, new String[] {
/*  30 */         "상대를 철괴로 타격할시에 천공의 투기장으로 이동하여 10초간 1:1 대결을 펼칩니다.", 
/*  31 */         "이때 상대는 다양한 디버프를 받고, 당신은 버프를 받습니다." });
/*  32 */       InitAbility(60, 0, true);
/*  33 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  39 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  40 */     if ((!EventManager.DamageGuard) && 
/*  41 */       (PlayerCheck(Event.getDamager())) && ((Event.getEntity() instanceof Player)) && (ItemCheck(ACC.DefaultItem))) {
/*  42 */       return 0;
/*     */     }
/*  44 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  49 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  50 */     Player p = (Player)Event.getEntity();
/*  51 */     Location l1 = p.getLocation();
/*  52 */     Location l2 = p.getLocation();
/*  53 */     Location l3 = p.getLocation();
/*  54 */     Location l = GetPlayer().getLocation();
/*  55 */     Location ll = GetPlayer().getLocation();
/*  56 */     l3.setY(l1.getY() + 52.0D);
/*  57 */     ll.setY(l.getY() + 52.0D);
/*  58 */     p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0), true);
/*  59 */     p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0), true);
/*  60 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0), true);
/*  61 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0), true);
/*  62 */     Timer timer = new Timer();
/*  63 */     timer.schedule(new Pauck(p, GetPlayer(), l1, l), 10000L);
/*  64 */     for (int j = 0; j <= 8; j++) {
/*  65 */       l2.setY(l1.getY() + j + 50.0D);
/*  66 */       for (int i = 0; i <= 5; i++)
/*     */       {
/*  68 */         l2.setX(l1.getX() + i);
/*     */         
/*  70 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*  71 */         for (int k = 0; k <= 5; k++) {
/*  72 */           l2.setZ(l1.getZ() + k);
/*     */           
/*  74 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/*  77 */       for (int i = 0; i <= 5; i++)
/*     */       {
/*  79 */         l2.setX(l1.getX() - i);
/*     */         
/*  81 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*  82 */         for (int k = 0; k <= 5; k++) {
/*  83 */           l2.setZ(l1.getZ() - k);
/*     */           
/*  85 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/*  88 */       for (int i = 0; i <= 5; i++)
/*     */       {
/*  90 */         l2.setX(l1.getX() - i);
/*     */         
/*  92 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*  93 */         for (int k = 0; k <= 5; k++) {
/*  94 */           l2.setZ(l1.getZ() + k);
/*     */           
/*  96 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/*  99 */       for (int i = 0; i <= 5; i++)
/*     */       {
/* 101 */         l2.setX(l1.getX() + i);
/*     */         
/* 103 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/* 104 */         for (int k = 0; k <= 5; k++) {
/* 105 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 107 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/*     */     }
/* 111 */     for (int j = 1; j <= 7; j++) {
/* 112 */       l2.setY(l1.getY() + j + 50.0D);
/* 113 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 115 */         l2.setX(l1.getX() + i);
/*     */         
/* 117 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 118 */         for (int k = 0; k <= 4; k++) {
/* 119 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 121 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 124 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 126 */         l2.setX(l1.getX() - i);
/*     */         
/* 128 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 129 */         for (int k = 0; k <= 4; k++) {
/* 130 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 132 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 135 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 137 */         l2.setX(l1.getX() - i);
/*     */         
/* 139 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 140 */         for (int k = 0; k <= 4; k++) {
/* 141 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 143 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 146 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 148 */         l2.setX(l1.getX() + i);
/*     */         
/* 150 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 151 */         for (int k = 0; k <= 4; k++) {
/* 152 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 154 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/* 158 */     l2.setY(l1.getY() + 7.0D + 50.0D);
/* 159 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 161 */       l2.setX(l1.getX() + 4.0D);
/* 162 */       l2.setZ(l1.getZ() + n);
/* 163 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 165 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 167 */       l2.setX(l1.getX() - 4.0D);
/* 168 */       l2.setZ(l1.getZ() + n);
/* 169 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 171 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 173 */       l2.setX(l1.getX() + 4.0D);
/* 174 */       l2.setZ(l1.getZ() - n);
/* 175 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 177 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 179 */       l2.setX(l1.getX() - 4.0D);
/* 180 */       l2.setZ(l1.getZ() - n);
/* 181 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 183 */     p.teleport(l3);
/* 184 */     GetPlayer().teleport(ll);
/*     */   }
/*     */   
/*     */   class Pauck extends TimerTask {
/*     */     Location l1;
/*     */     Location l2;
/*     */     Player p1;
/*     */     Player p2;
/*     */     
/* 193 */     Pauck(Player pp1, Player pp2, Location locc, Location locc2) { this.p1 = pp1;
/* 194 */       this.p2 = pp2;
/* 195 */       this.l1 = locc;
/* 196 */       this.l2 = locc2;
/*     */     }
/*     */     
/*     */     public void run() {
/* 200 */       this.p1.teleport(this.l1);
/* 201 */       this.p2.teleport(this.l2);
/* 202 */       Location l4 = this.p1.getLocation();
/* 203 */       Location l5 = this.p1.getLocation();
/* 204 */       for (int j = 0; j <= 8; j++) {
/* 205 */         l5.setY(this.l1.getY() + j + 50.0D);
/* 206 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 208 */           l5.setX(l4.getX() + i);
/*     */           
/* 210 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 211 */           for (int k = 0; k <= 5; k++) {
/* 212 */             l5.setZ(l4.getZ() + k);
/*     */             
/* 214 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 217 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 219 */           l5.setX(l4.getX() - i);
/*     */           
/* 221 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 222 */           for (int k = 0; k <= 5; k++) {
/* 223 */             l5.setZ(l4.getZ() - k);
/*     */             
/* 225 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 228 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 230 */           l5.setX(l4.getX() - i);
/*     */           
/* 232 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 233 */           for (int k = 0; k <= 5; k++) {
/* 234 */             l5.setZ(l4.getZ() + k);
/*     */             
/* 236 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 239 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 241 */           l5.setX(l4.getX() + i);
/*     */           
/* 243 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 244 */           for (int k = 0; k <= 5; k++) {
/* 245 */             l5.setZ(l4.getZ() - k);
/*     */             
/* 247 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Gladiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */