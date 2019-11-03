/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class MachineGun extends AbilityBase
/*     */ {
/*  28 */   private int bullet = 0;
/*     */   private int item;
/*  30 */   private HashMap<Player, Boolean> relo = new HashMap();
/*     */   
/*     */   public MachineGun() {
/*  33 */     InitAbility("기관총", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  34 */       "고속으로 화살을 발사합니다. 금괴를 들고 오른클릭을 누르면 연사가", 
/*  35 */       "가능합니다. 철괴를 탄창으로 사용하며 한 탄창은 30발입니다.", 
/*  36 */       "피격시 10% 확률로 방어력을 무시하고 체력을 2 감소시키는", 
/*  37 */       "크리티컬이 발생합니다. 기본 탄환 데미지는 1입니다." });
/*  38 */     InitAbility(0, 0, true, AbilityBase.ShowText.Custom_Text);
/*     */     
/*  40 */     RegisterRightClickEvent();
/*  41 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 3));
/*  42 */     EventManager.onProjectileHitEvent.add(new EventData(this, 5));
/*  43 */     this.item = Material.GOLD_INGOT.getId();
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  47 */     switch (CustomData) {
/*     */     case 1: 
/*  49 */       PlayerInteractEvent Event0 = (PlayerInteractEvent)event;
/*  50 */       if ((!EventManager.DamageGuard) && 
/*  51 */         (PlayerCheck(Event0.getPlayer())) && (ItemCheck(this.item))) {
/*  52 */         if (this.bullet != 0) {
/*  53 */           return 10;
/*     */         }
/*     */         
/*     */ 
/*  57 */         if (GetPlayer().getInventory().contains(Material.IRON_INGOT)) {
/*  58 */           return 20;
/*     */         }
/*  60 */         GetPlayer().sendMessage(ChatColor.RED + "탄창이 없습니다.");
/*     */         
/*  62 */         if (this.relo.containsKey(GetPlayer()))
/*     */         {
/*  64 */           GetPlayer().sendMessage(ChatColor.RED + "장전중입니다.");
/*     */         }
/*     */       }
/*  67 */       break;
/*     */     case 3: 
/*  69 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  70 */       if ((Event1.getDamager() instanceof Arrow)) {
/*  71 */         Arrow a = (Arrow)Event1.getDamager();
/*  72 */         if (PlayerCheck((Player)a.getShooter())) {
/*  73 */           if (((Event1.getEntity() instanceof Player)) && 
/*  74 */             ((Player)a.getShooter() == 
/*  75 */             (Player)Event1
/*  76 */             .getEntity()))
/*  77 */             return -1;
/*  78 */           return 3;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 5: 
/*  83 */       ProjectileHitEvent Event2 = (ProjectileHitEvent)event;
/*  84 */       if ((Event2.getEntity() instanceof Arrow)) {
/*  85 */         Arrow a = (Arrow)Event2.getEntity();
/*  86 */         if (((a.getShooter() instanceof Player)) && 
/*  87 */           (PlayerCheck((Player)a.getShooter()))) {
/*  88 */           a.remove();
/*  89 */           return -2;
/*     */         }
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*  96 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/* 102 */     switch (CustomData) {
/*     */     case 3: 
/* 104 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/* 105 */       Event0.setDamage(1);
/* 106 */       if (((Event0.getEntity() instanceof Player)) && (Math.random() <= 0.1D)) {
/* 107 */         Player p = (Player)Event0.getEntity();
/* 108 */         p.getWorld().createExplosion(Event0.getEntity().getLocation(), 
/* 109 */           0.0F);
/* 110 */         if (((Damageable)p).getHealth() > 2.0D) {
/* 111 */           p.setHealth(((Damageable)p).getHealth() - 2);
/*     */         }
/*     */       }
/* 114 */       break;
/*     */     case 10: 
/* 116 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/* 117 */       if (this.bullet % 5 == 0) {
/* 118 */         Event1.getPlayer().sendMessage(
/* 119 */           String.format(ChatColor.AQUA + "남은 탄환 : " + 
/* 120 */           ChatColor.WHITE + "%d개", new Object[] {
/* 121 */           Integer.valueOf(this.bullet) }));
/*     */       }
/* 123 */       this.bullet -= 1;
/* 124 */       Event1.getPlayer().shootArrow();
/* 125 */       break;
/*     */     case 20: 
/* 127 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 128 */       if (!this.relo.containsKey(GetPlayer())) {
/* 129 */         Event2.getPlayer().sendMessage("탄환이 다 떨어졌습니다. 장전합니다 [3초소요]");
/* 130 */         this.relo.put(Event2.getPlayer(), Boolean.valueOf(true));
/* 131 */         Timer timer = new Timer();
/* 132 */         timer.schedule(new onReload(), 3000L);
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */   
/*     */   class onReload extends TimerTask
/*     */   {
/*     */     onReload() {}
/*     */     
/*     */     public void run()
/*     */     {
/* 144 */       PlayerInventory inv = MachineGun.this.GetPlayer().getInventory();
/* 145 */       int sell = inv.first(Material.IRON_INGOT);
/* 146 */       if (inv.getItem(sell).getAmount() == 1) {
/* 147 */         inv.clear(sell);
/*     */       } else {
/* 149 */         inv.getItem(sell).setAmount(inv.getItem(sell).getAmount() - 1);
/*     */       }
/* 151 */       MachineGun.this.GetPlayer().updateInventory();
/* 152 */       MachineGun.this.bullet = 30;
/* 153 */       MachineGun.this.GetPlayer().sendMessage(ChatColor.GREEN + "재장전 완료");
/* 154 */       MachineGun.this.relo.remove(MachineGun.this.GetPlayer());
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\MachineGun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */