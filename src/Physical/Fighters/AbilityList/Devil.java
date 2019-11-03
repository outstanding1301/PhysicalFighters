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
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Devil
/*     */   extends AbilityBase
/*     */ {
/*     */   public Devil()
/*     */   {
/*  31 */     if (PhysicalFighters.Gods) {
/*  32 */       InitAbility("악마", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.GOD, new String[] {
/*  33 */         "상대를 철괴로 타격할시에 공중의 투기장으로 이동하여 20초간 1:1 대결을 펼칩니다.", 
/*  34 */         "이때 10초간 상대는 다양한 디버프를 받고, 당신은 버프를 받습니다.", 
/*  35 */         "당신은 불데미지, 낙하데미지를 받지 않습니다." });
/*  36 */       InitAbility(80, 0, true);
/*  37 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*  38 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  44 */     if (CustomData == 0)
/*     */     {
/*  46 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  47 */       if ((!EventManager.DamageGuard) && 
/*  48 */         (PlayerCheck(Event.getDamager())) && ((Event.getEntity() instanceof Player)) && (ItemCheck(ACC.DefaultItem))) {
/*  49 */         return 0;
/*     */       }
/*     */     }
/*  52 */     else if (CustomData == 3) {
/*  53 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/*  54 */       if (PlayerCheck(Event2.getEntity())) {
/*  55 */         if ((Event2.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
/*  56 */           (Event2.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
/*  57 */           (Event2.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
/*  58 */           Event2.setCancelled(true);
/*     */         }
/*  60 */         else if (Event2.getCause() == EntityDamageEvent.DamageCause.FALL) {
/*  61 */           GetPlayer().sendMessage(
/*  62 */             ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/*  63 */           Event2.setCancelled(true);
/*     */         }
/*     */       }
/*     */     }
/*  67 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  72 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  73 */     Player p = (Player)Event.getEntity();
/*  74 */     Location l1 = p.getLocation();
/*  75 */     Location l2 = p.getLocation();
/*  76 */     Location l3 = p.getLocation();
/*  77 */     Location l = GetPlayer().getLocation();
/*  78 */     Location ll = GetPlayer().getLocation();
/*  79 */     l3.setY(l1.getY() + 83.0D);
/*  80 */     ll.setY(l.getY() + 83.0D);
/*  81 */     p.teleport(l3);
/*  82 */     GetPlayer().teleport(ll);
/*  83 */     p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0), true);
/*  84 */     p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0), true);
/*  85 */     p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
/*  86 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0), true);
/*  87 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0), true);
/*  88 */     Timer timer = new Timer();
/*  89 */     timer.schedule(new Pauck(p, GetPlayer(), l1, l), 20000L);
/*  90 */     for (int j = 0; j <= 8; j++) {
/*  91 */       l2.setY(l1.getY() + j + 80.0D);
/*  92 */       for (int i = 0; i <= 5; i++)
/*     */       {
/*  94 */         l2.setX(l1.getX() + i);
/*     */         
/*  96 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*  97 */         for (int k = 0; k <= 5; k++) {
/*  98 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 100 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/* 103 */       for (int i = 0; i <= 5; i++)
/*     */       {
/* 105 */         l2.setX(l1.getX() - i);
/*     */         
/* 107 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/* 108 */         for (int k = 0; k <= 5; k++) {
/* 109 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 111 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/* 114 */       for (int i = 0; i <= 5; i++)
/*     */       {
/* 116 */         l2.setX(l1.getX() - i);
/*     */         
/* 118 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/* 119 */         for (int k = 0; k <= 5; k++) {
/* 120 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 122 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/* 125 */       for (int i = 0; i <= 5; i++)
/*     */       {
/* 127 */         l2.setX(l1.getX() + i);
/*     */         
/* 129 */         p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/* 130 */         for (int k = 0; k <= 5; k++) {
/* 131 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 133 */           p.getWorld().getBlockAt(l2).setType(Material.BEDROCK);
/*     */         }
/*     */       }
/*     */     }
/* 137 */     for (int j = 1; j <= 7; j++) {
/* 138 */       l2.setY(l1.getY() + j + 80.0D);
/* 139 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 141 */         l2.setX(l1.getX() + i);
/*     */         
/* 143 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/* 144 */         for (int k = 0; k <= 4; k++) {
/* 145 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 147 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/* 150 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 152 */         l2.setX(l1.getX() - i);
/*     */         
/* 154 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/* 155 */         for (int k = 0; k <= 4; k++) {
/* 156 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 158 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/* 161 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 163 */         l2.setX(l1.getX() - i);
/*     */         
/* 165 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/* 166 */         for (int k = 0; k <= 4; k++) {
/* 167 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 169 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/* 172 */       for (int i = 0; i <= 4; i++)
/*     */       {
/* 174 */         l2.setX(l1.getX() + i);
/*     */         
/* 176 */         p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/* 177 */         for (int k = 0; k <= 4; k++) {
/* 178 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 180 */           p.getWorld().getBlockAt(l2).setType(Material.NETHERRACK);
/*     */         }
/*     */       }
/*     */     }
/* 184 */     for (int j = 2; j <= 6; j++) {
/* 185 */       l2.setY(l1.getY() + j + 80.0D);
/* 186 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 188 */         l2.setX(l1.getX() + i);
/*     */         
/* 190 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 191 */         for (int k = 0; k <= 3; k++) {
/* 192 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 194 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 197 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 199 */         l2.setX(l1.getX() - i);
/*     */         
/* 201 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 202 */         for (int k = 0; k <= 3; k++) {
/* 203 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 205 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 208 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 210 */         l2.setX(l1.getX() - i);
/*     */         
/* 212 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 213 */         for (int k = 0; k <= 3; k++) {
/* 214 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 216 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/* 219 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 221 */         l2.setX(l1.getX() + i);
/*     */         
/* 223 */         p.getWorld().getBlockAt(l2).setType(Material.AIR);
/* 224 */         for (int k = 0; k <= 3; k++) {
/* 225 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 227 */           p.getWorld().getBlockAt(l2).setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/* 231 */     l2.setY(l1.getY() + 6.0D + 80.0D);
/* 232 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 234 */       l2.setX(l1.getX() + 4.0D);
/* 235 */       l2.setZ(l1.getZ() + n);
/* 236 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 238 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 240 */       l2.setX(l1.getX() - 4.0D);
/* 241 */       l2.setZ(l1.getZ() + n);
/* 242 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 244 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 246 */       l2.setX(l1.getX() + 4.0D);
/* 247 */       l2.setZ(l1.getZ() - n);
/* 248 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/* 250 */     for (int n = 0; n <= 4; n++)
/*     */     {
/* 252 */       l2.setX(l1.getX() - 4.0D);
/* 253 */       l2.setZ(l1.getZ() - n);
/* 254 */       p.getWorld().getBlockAt(l2).setType(Material.TORCH);
/*     */     }
/*     */   }
/*     */   
/*     */   class Pauck extends TimerTask {
/*     */     Location l1;
/*     */     Location l2;
/*     */     Player p1;
/*     */     Player p2;
/*     */     
/* 264 */     Pauck(Player pp1, Player pp2, Location locc, Location locc2) { this.p1 = pp1;
/* 265 */       this.p2 = pp2;
/* 266 */       this.l1 = locc;
/* 267 */       this.l2 = locc2;
/*     */     }
/*     */     
/*     */     public void run() {
/* 271 */       this.p1.teleport(this.l1);
/* 272 */       this.p2.teleport(this.l2);
/* 273 */       Location l4 = this.p1.getLocation();
/* 274 */       Location l5 = this.p1.getLocation();
/* 275 */       for (int j = 0; j <= 8; j++) {
/* 276 */         l5.setY(this.l1.getY() + j + 80.0D);
/* 277 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 279 */           l5.setX(l4.getX() + i);
/*     */           
/* 281 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 282 */           for (int k = 0; k <= 5; k++) {
/* 283 */             l5.setZ(l4.getZ() + k);
/*     */             
/* 285 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 288 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 290 */           l5.setX(l4.getX() - i);
/*     */           
/* 292 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 293 */           for (int k = 0; k <= 5; k++) {
/* 294 */             l5.setZ(l4.getZ() - k);
/*     */             
/* 296 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 299 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 301 */           l5.setX(l4.getX() - i);
/*     */           
/* 303 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 304 */           for (int k = 0; k <= 5; k++) {
/* 305 */             l5.setZ(l4.getZ() + k);
/*     */             
/* 307 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/* 310 */         for (int i = 0; i <= 5; i++)
/*     */         {
/* 312 */           l5.setX(l4.getX() + i);
/*     */           
/* 314 */           this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/* 315 */           for (int k = 0; k <= 5; k++) {
/* 316 */             l5.setZ(l4.getZ() - k);
/*     */             
/* 318 */             this.p1.getWorld().getBlockAt(l5).setType(Material.AIR);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Devil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */