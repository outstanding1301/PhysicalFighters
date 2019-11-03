/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Luffy extends AbilityBase
/*     */ {
/*     */   private int item;
/*     */   
/*     */   public Luffy()
/*     */   {
/*  27 */     InitAbility("루피", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  28 */       "철괴를 들고 왼쪽클릭을 하면 주먹질을 합니다 [쿨타임 없음]", 
/*  29 */       "금괴를 들고 왼쪽클릭을 하면 30초간 속도,점프력,공격력,방어력이 높아집니다. [체력 5 소모, 쿨타임없음]", 
/*  30 */       "버프스킬을 사용시에  부작용이 있습니다.", 
/*  31 */       "*주의* 금괴를 들고 왼쪽클릭을 난타하시다가 사망하실 수 있습니다." });
/*  32 */     InitAbility(0, 0, true, AbilityBase.ShowText.Custom_Text);
/*  33 */     RegisterLeftClickEvent();
/*  34 */     this.item = Material.IRON_INGOT.getId();
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  39 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  40 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(this.item)) && !EventManager.DamageGuard)
/*     */     {
/*  42 */       return 1;
/*     */     }
/*  44 */     if ((PlayerCheck(Event.getPlayer())) && (((Damageable)Event.getPlayer()).getHealth() >= 6.0D) && (ItemCheck(Material.GOLD_INGOT.getId())))
/*     */     {
/*  46 */       return 2;
/*     */     }
/*  48 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/*  52 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  53 */     Player p = Event.getPlayer();
/*  54 */     Location l = Event.getPlayer().getLocation();
/*  55 */     Location l2 = Event.getPlayer().getLocation();
/*  56 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/*  57 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/*  58 */     switch (CustomData)
/*     */     {
/*     */     case 1: 
/*  61 */       Timer timer = new Timer();
/*  62 */       for (int i = 1; i < 5; i++) {
/*  63 */         l2.setX(l.getX() + (1 * i + 1) * (
/*  64 */           Math.sin(degrees) * Math.cos(ydeg)));
/*  65 */         l2.setY(l.getY() + (1 * i + 1) * Math.sin(ydeg));
/*  66 */         l2.setZ(l.getZ() + (1 * i + 1) * (
/*  67 */           Math.cos(degrees) * Math.cos(ydeg)));
/*  68 */         if (l2.getWorld().getBlockAt(l2).getType() != Material.SANDSTONE)
/*  69 */           timer.schedule(new ExplosionTimer2(l2.getWorld().getBlockAt(l2).getTypeId(), l2.getWorld().getBlockAt(l2)), 70L);
/*  70 */         l2.getWorld().getBlockAt(l2).setTypeId(24);
/*  71 */         Player[] List = Bukkit.getOnlinePlayers();
/*  72 */         Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = List).length; for (int k = 0; k < j; k++) { Player pp = arrayOfPlayer1[k];
/*  73 */           if (pp != GetPlayer())
/*     */           {
/*  75 */             Location loc = pp.getLocation();
/*  76 */             if (l2.getWorld().getBlockAt(l2).getLocation().distance(loc) <= 3.0D)
/*     */             {
/*  78 */               pp.damage(1, p);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  83 */       break;
/*     */     case 2: 
/*  85 */       p.setHealth(((Damageable)p).getHealth() - 5);
/*  86 */       p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 0), true);
/*  87 */       p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0), true);
/*  88 */       p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0), true);
/*  89 */       p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), true);
/*  90 */       p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0), true);
/*  91 */       p.sendMessage(org.bukkit.ChatColor.GREEN + "기어세컨드를 사용하였습니다.");
/*     */     }
/*     */   }
/*     */   
/*     */   class ExplosionTimer extends TimerTask {
/*     */     World world;
/*     */     Location location;
/*     */     Location location2;
/*     */     
/*     */     ExplosionTimer(int blockid, Block block) {
/* 101 */       this.world = block.getWorld();
/* 102 */       this.location = block.getLocation();
/* 103 */       this.location2 = block.getLocation();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 108 */     public void run() { this.world.getBlockAt(this.location).breakNaturally(); }
/*     */   }
/*     */   
/*     */   class ExplosionTimer2 extends TimerTask {
/*     */     World world;
/*     */     Location location;
/*     */     Location location2;
/*     */     private int blockd;
/*     */     
/* 117 */     ExplosionTimer2(int blockid, Block block) { this.world = block.getWorld();
/* 118 */       this.location = block.getLocation();
/* 119 */       this.location2 = block.getLocation();
/* 120 */       this.blockd = blockid;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 125 */       this.world.getBlockAt(this.location).setTypeId(this.blockd);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Luffy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */