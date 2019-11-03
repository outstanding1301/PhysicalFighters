/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Ahri extends AbilityBase
/*     */ {
/*     */   public Ahri()
/*     */   {
/*  30 */     if (Physical.Fighters.PhysicalFighters.SRankUsed) {
/*  31 */       InitAbility("아리", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/*  32 */         "눈덩이를 던져, 맞은 적을 자신에게 무작정 걸어오게 만듭니다." });
/*  33 */       InitAbility(20, 0, true);
/*  34 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  35 */       EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  36 */       EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  37 */       EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  43 */     switch (CustomData) {
/*     */     case 0: 
/*  45 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  46 */       if ((!EventManager.DamageGuard) && 
/*  47 */         ((Event0.getDamager() instanceof Snowball))) {
/*  48 */         Snowball a = (Snowball)Event0.getDamager();
/*  49 */         if (PlayerCheck((Player)a.getShooter())) {
/*  50 */           if (((Event0.getEntity() instanceof Player)) && 
/*  51 */             ((Player)a.getShooter() == 
/*  52 */             (Player)Event0
/*  53 */             .getEntity()))
/*  54 */             return 9999;
/*  55 */           Location l = GetPlayer().getLocation();
/*  56 */           Location l1 = Event0.getEntity().getLocation();
/*  57 */           if (l.distance(l1) < 10.0D)
/*  58 */             return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  63 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  64 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  65 */         (Event1.getItemDrop().getItemStack().getType() == Material.SNOW_BALL)) {
/*  66 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  67 */         if (!inv.contains(Material.SNOW_BALL, 16)) {
/*  68 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  73 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  74 */       if (PlayerCheck(Event2.getPlayer()))
/*  75 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  78 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  79 */       if (PlayerCheck(Event3.getEntity()))
/*  80 */         return 3;
/*     */       break;
/*     */     }
/*  83 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  88 */     switch (CustomData)
/*     */     {
/*     */ 
/*     */     case 0: 
/*  92 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  93 */       Entity pe = Event0.getEntity();
/*  94 */       Location pl = GetPlayer().getLocation();
/*  95 */       Location tl = pe.getLocation();
/*  96 */       Timer timer = new Timer();
/*  97 */       timer.schedule(new Timerr(GetPlayer(), pe), 0L, 80L);
/*     */       
/*     */ 
/* 100 */       break;
/*     */     case 1: 
/* 102 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 103 */       Event1.getPlayer().sendMessage(
/* 104 */         ChatColor.RED + "소유한 눈덩이가 16개 이하일시 못버립니다.");
/* 105 */       Event1.setCancelled(true);
/* 106 */       break;
/*     */     case 2: 
/* 108 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 109 */       Event2.getPlayer().sendMessage(ChatColor.GREEN + "눈덩이가 지급됩니다.");
/* 110 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 111 */       inv.setItem(8, new ItemStack(332, 64));
/* 112 */       inv.setItem(7, new ItemStack(332, 64));
/* 113 */       break;
/*     */     case 3: 
/* 115 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 116 */       List<ItemStack> itemlist = Event3.getDrops();
/* 117 */       for (int l = 0; l < itemlist.size(); l++)
/* 118 */         if (((ItemStack)itemlist.get(l)).getType() == Material.SNOW_BALL)
/* 119 */           itemlist.remove(l);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 125 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 126 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 131 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 132 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */   
/*     */   public class Timerr extends TimerTask { private Player p;
/*     */     private Entity t;
/* 137 */     private int x = 0;
/*     */     private Location l;
/*     */     private Location l1;
/*     */     
/* 141 */     public Timerr(Player pp, Entity tt) { this.p = pp;
/* 142 */       this.t = tt;
/* 143 */       this.l = this.p.getLocation();
/* 144 */       this.l1 = this.t.getLocation();
/*     */     }
/*     */     
/*     */     public void run() {
/* 148 */       if (this.x > 30) {
/* 149 */         cancel();
/*     */       }
/* 151 */       this.l = this.p.getLocation();
/* 152 */       this.l1 = this.t.getLocation();
/*     */       
/*     */ 
/* 155 */       this.l1.setX(((((((this.l1.getX() + this.l.getX()) / 2.0D + this.l1.getX()) / 2.0D + this.l1.getX()) / 2.0D + this.l1.getX()) / 2.0D + this.l1.getX()) / 2.0D + this.l1.getX()) / 2.0D);
/* 156 */       this.l1.setZ(((((((this.l1.getZ() + this.l.getZ()) / 2.0D + this.l1.getZ()) / 2.0D + this.l1.getZ()) / 2.0D + this.l1.getZ()) / 2.0D + this.l1.getZ()) / 2.0D + this.l1.getZ()) / 2.0D);
/* 157 */       this.t.teleport(this.l1);
/* 158 */       this.x += 1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Ahri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */