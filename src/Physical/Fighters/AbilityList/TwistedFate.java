/*     */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class TwistedFate extends AbilityBase
/*     */ {
/*  27 */   int Card = 0;
/*  28 */   int RCard = 0;
/*  29 */   int DCard = 0;
/*  30 */   Timer t = new Timer();
/*  31 */   Timer tt = new Timer();
/*     */   
/*     */   public TwistedFate() {
/*  34 */     InitAbility("트위스티드 페이트", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  35 */       "철괴를 들고 우클릭시 카드를 뽑으며, 좌클릭시 바라보는 방향으로 카드를 날립니다.", 
/*  36 */       "카드는 순서대로 빨강색, 파랑색, 황금색이 있습니다.", 
/*  37 */       ChatColor.RED + "빨강색 카드" + ChatColor.WHITE + "는 15의 데미지를 주며, 상대와 주변 플레이어의 이동속도를 느리게합니다.", 
/*  38 */       ChatColor.BLUE + "파랑색 카드" + ChatColor.WHITE + "는 20의 데미지를 주며, 본 스킬의 재사용 대기시간을 초기화 시켜줍니다.", 
/*  39 */       ChatColor.GOLD + "황금색 카드" + ChatColor.WHITE + "는 10의 데미지를 주며, 상대를 2초간 못움직이게합니다." });
/*  40 */     InitAbility(20, 0, true);
/*  41 */     RegisterLeftClickEvent();
/*  42 */     RegisterRightClickEvent();
/*  43 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 2));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  48 */     if (CustomData == 0) {
/*  49 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/*  50 */       if ((PlayerCheck(e.getPlayer())) && 
/*  51 */         ((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) && 
/*  52 */         (ItemCheck(Material.IRON_INGOT.getId()))) {
/*  53 */         if (this.RCard != 0)
/*     */         {
/*  55 */           Arrow a = e.getPlayer().shootArrow();
/*  56 */           a.setVelocity(a.getVelocity().multiply(5));
/*  57 */           a.setShooter(e.getPlayer());
/*  58 */           e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.SLIME_ATTACK, 3.0F, 3.0F);
/*  59 */           int i = this.RCard;
/*  60 */           this.DCard = i;
/*  61 */           this.RCard = 0;
/*  62 */           Bukkit.broadcastMessage(this.DCard + "," + this.RCard);
/*     */         } else {
/*  64 */           e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "카드를 뽑아주세요 (철괴우클릭)");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  69 */     if (CustomData == 1) {
/*  70 */       final PlayerInteractEvent e = (PlayerInteractEvent)event;
/*  71 */       if ((PlayerCheck(e.getPlayer())) && 
/*  72 */         ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
/*  73 */         (ItemCheck(Material.IRON_INGOT.getId()))) {
/*  74 */         if (this.Card != 0) {
/*  75 */           if (this.Card == 1) {
/*  76 */             this.Card = 0;
/*  77 */             this.RCard = 1;
/*  78 */             e.getPlayer().sendMessage(ChatColor.RED + "빨강색 카드를 선택했습니다.");
/*  79 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
/*     */           }
/*  81 */           if (this.Card == 2) {
/*  82 */             this.Card = 0;
/*  83 */             this.RCard = 2;
/*  84 */             e.getPlayer().sendMessage(ChatColor.BLUE + "파랑색 카드를 선택했습니다.");
/*  85 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1.0F, 3.0F);
/*     */           }
/*  87 */           if (this.Card == 3) {
/*  88 */             this.Card = 0;
/*  89 */             this.RCard = 3;
/*  90 */             e.getPlayer().sendMessage(ChatColor.GOLD + "황금색 카드를 선택했습니다.");
/*  91 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 3.0F, 1.0F);
/*     */           }
/*  93 */           this.tt = new Timer();
/*  94 */           this.tt.schedule(new TimerTask()
/*     */           {
/*     */             public void run() {
/*  97 */               TwistedFate.this.RCard = 0;
/*  98 */               e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "카드를 던지지 못했습니다.");
/*     */             }
/* 100 */           }, 5000L);
/* 101 */           this.t.cancel();
/* 102 */           return -1;
/*     */         }
/* 104 */         return 1;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 109 */     if (CustomData == 2) {
/* 110 */       EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
/* 111 */       if ((this.DCard != 0) && 
/* 112 */         ((e.getDamager() instanceof Arrow)) && ((e.getEntity() instanceof LivingEntity))) {
/* 113 */         Player p = (Player)((Arrow)e.getDamager()).getShooter();
/* 114 */         LivingEntity t = (LivingEntity)e.getEntity();
/* 115 */         if (PlayerCheck(p)) {
/* 116 */           if (this.DCard == 1) {
/* 117 */             p.sendMessage(ChatColor.RED + "빨강색 카드를 맞춰 주변 플레이어의 속도를 늦춥니다..");
/* 118 */             t.getWorld().playEffect(t.getLocation(), org.bukkit.Effect.ENDER_SIGNAL, 1);
/* 119 */             p.playSound(p.getLocation(), Sound.BLAZE_BREATH, 3.0F, -1.0F);
/* 120 */             for (Player l : Bukkit.getOnlinePlayers()) {
/* 121 */               if ((l != p) && (l.getLocation().distance(t.getLocation()) <= 3.0D)) {
/* 122 */                 l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
/* 123 */                 l.playSound(l.getLocation(), Sound.BLAZE_BREATH, 3.0F, -1.0F);
/* 124 */                 l.damage(15);
/*     */               }
/*     */             }
/* 127 */             this.DCard = 0;
/*     */           }
/* 129 */           if (this.DCard == 2) {
/* 130 */             if (getCool() - 10 > 0) {
/* 131 */               setCool(getCool() - 10);
/*     */             } else
/* 133 */               AbilityCTimerCancel();
/* 134 */             p.sendMessage(ChatColor.BLUE + "파랑색 카드를 맞춰 쿨타임이 10초 감소되었습니다.");
/* 135 */             p.playSound(p.getLocation(), Sound.SPLASH2, 3.0F, 3.0F);
/* 136 */             t.damage(20);
/* 137 */             this.DCard = 0;
/*     */           }
/* 139 */           if (this.DCard == 3) {
/* 140 */             p.sendMessage(ChatColor.GOLD + "황금색 카드를 맞춰 상대를 2초간 못움직이게합니다.");
/* 141 */             p.playSound(p.getLocation(), Sound.GLASS, 3.0F, 3.0F);
/* 142 */             t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 10));
/* 143 */             t.damage(10);
/* 144 */             this.DCard = 0;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 150 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/* 155 */     if (CustomData == 1) {
/* 156 */       final PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 157 */       this.t = new Timer();
/* 158 */       this.t.schedule(new TimerTask() {
/*     */         int i;
/*     */         
/*     */         public void run() {
/* 162 */           if (this.i == 6) {
/* 163 */             e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "카드를 뽑지 못했습니다.");
/* 164 */             TwistedFate.this.Card = 0;
/* 165 */             cancel();
/*     */           }
/* 167 */           if (TwistedFate.this.Card == 0) {
/* 168 */             e.getPlayer().sendMessage(ChatColor.RED + "빨강색 카드");
/* 169 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
/* 170 */             TwistedFate.this.Card = 1;
/* 171 */           } else if (TwistedFate.this.Card == 1) {
/* 172 */             e.getPlayer().sendMessage(ChatColor.BLUE + "파랑색 카드");
/* 173 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
/* 174 */             TwistedFate.this.Card = 2;
/* 175 */           } else if (TwistedFate.this.Card == 2) {
/* 176 */             e.getPlayer().sendMessage(ChatColor.GOLD + "황금색 카드");
/* 177 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
/* 178 */             TwistedFate.this.Card = 3;
/* 179 */           } else if (TwistedFate.this.Card == 3) {
/* 180 */             e.getPlayer().sendMessage(ChatColor.RED + "빨강색 카드");
/* 181 */             e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
/* 182 */             TwistedFate.this.Card = 1;
/*     */           }
/* 184 */           this.i += 1;
/*     */         }
/* 186 */       }, 0L, 700L);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\TwistedFate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */