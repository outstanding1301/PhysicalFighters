/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class Thor extends AbilityBase
/*     */ {
/*  23 */   int Attack = 0;
/*     */   
/*     */   public Thor() {
/*  26 */     if ((!PhysicalFighters.Toner) && 
/*  27 */       (PhysicalFighters.SRankUsed)) {
/*  28 */       InitAbility("토르", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.GOD, new String[] {
/*  29 */         "금도끼는 묠니르입니다. 묠니르의 기본데미지는 8입니다.", 
/*  30 */         "묠니르를 들고 우클릭시 묠니르에 번개의 힘을 내리치며 주변의 플레이어에게 5의 데미지를 주고,", 
/*  31 */         "다음 공격에 +3의 데미지를 농축시킵니다. (6번까지 중첩됩니다.)" });
/*  32 */       InitAbility(8, 0, true);
/*  33 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  34 */       RegisterRightClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack m() {
/*  39 */     ItemStack is = new ItemStack(Material.GOLD_AXE);
/*  40 */     ItemMeta im = is.getItemMeta();
/*  41 */     im.setDisplayName(ChatColor.YELLOW + "묠니르");
/*  42 */     is.setItemMeta(im);
/*  43 */     return is;
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  48 */     if (CustomData == 1) {
/*  49 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  50 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(m().getTypeId())) && (!EventManager.DamageGuard)) {
/*  51 */         Event.getPlayer().getItemInHand().setDurability((short)0);
/*  52 */         return 0;
/*     */       }
/*     */     }
/*  55 */     if (CustomData == 0) {
/*  56 */       EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
/*  57 */       if ((PlayerCheck(e.getDamager())) && ((e.getEntity() instanceof LivingEntity))) {
/*  58 */         Player p = (Player)e.getDamager();
/*  59 */         LivingEntity t = (LivingEntity)e.getEntity();
/*  60 */         if ((p.getItemInHand().getType() == m().getType()) && 
/*  61 */           (p.getItemInHand().hasItemMeta()) && 
/*  62 */           (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "묠니르"))) {
/*  63 */           e.setDamage(8);
/*  64 */           if (this.Attack > 0) {
/*  65 */             t.damage(3 * this.Attack);
/*  66 */             t.getWorld().strikeLightning(t.getLocation());
/*  67 */             p.sendMessage(ChatColor.YELLOW + "묠니르에 농축된 번개의 데미지를 추가로 입혔습니다.");
/*  68 */             this.Attack = 0;
/*     */           }
/*  70 */           p.getItemInHand().setDurability((short)0);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  76 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  81 */     if (CustomData == 0) {
/*  82 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  83 */       Player p = Event.getPlayer();
/*  84 */       World w = p.getWorld();
/*  85 */       org.bukkit.Location loc = p.getLocation();
/*  86 */       w.strikeLightningEffect(loc);
/*  87 */       w.strikeLightningEffect(loc);
/*  88 */       ExplosionDMG(loc, 3, 5);
/*  89 */       if (this.Attack < 6) {
/*  90 */         this.Attack += 1;
/*  91 */         p.sendMessage(ChatColor.YELLOW + "묠니르에 농축된 번개 : (" + this.Attack + "/6)");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void A_SetEvent(Player p)
/*     */   {
/* 100 */     p.getInventory().setItem(8, m());
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Thor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */