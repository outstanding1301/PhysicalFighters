/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
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
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.BlockIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Genji
/*     */   extends AbilityBase
/*     */ {
/*     */   int aaa;
/*     */   boolean po;
/*     */   
/*     */   public Genji()
/*     */   {
/*  39 */     if (PhysicalFighters.SRankUsed) {
/*  40 */       InitAbility(
/*  41 */         "겐지", 
/*  42 */         AbilityBase.Type.Active_Immediately, 
/*  43 */         AbilityBase.Rank.S, 
/*  44 */         new String[] {
/*  45 */         "철괴나 칼을 들고 쉬프트시 앞으로 돌진하며 앞의 모든 적에게 큰 데미지를 줍니다.(질풍참)", 
/*  46 */         "질풍참을 사용시 5초간 칼의 사거리가 증가하며 5의 추가데미지를 입힙니다.", 
/*  47 */         "*낙하데미지를 받지않습니다." });
/*  48 */       InitAbility(20, 0, true);
/*  49 */       EventManager.onPlayerToggleSneakEvent.add(new EventData(this, 7));
/*  50 */       RegisterLeftClickEvent();
/*  51 */       RegisterRightClickEvent();
/*  52 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*  53 */       EventManager.onPlayerInteract.add(new EventData(this, 4));
/*  54 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 5));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  61 */     if (CustomData == 7) {
/*  62 */       PlayerToggleSneakEvent e = (PlayerToggleSneakEvent)event;
/*  63 */       if ((PlayerCheck(e.getPlayer())) && (e.isSneaking())) {
/*  64 */         return 1;
/*     */       }
/*     */     }
/*     */     
/*  68 */     if (CustomData == 3) {
/*  69 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/*  70 */       if ((PlayerCheck(Event2.getEntity())) && 
/*  71 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FALL)) {
/*  72 */         Event2.setCancelled(true);
/*  73 */         GetPlayer().sendMessage(
/*  74 */           ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/*     */       }
/*     */     }
/*  77 */     if (CustomData == 4) {
/*  78 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/*  79 */       if ((PlayerCheck(e.getPlayer())) && 
/*  80 */         (isSword(e.getPlayer().getItemInHand())) && (this.po)) {
/*  81 */         BlockIterator bi = new BlockIterator(e.getPlayer(), 6);
/*  82 */         while (bi.hasNext()) {
/*  83 */           Block bb = bi.next();
/*  84 */           ExplosionDMGPotion(e.getPlayer(), bb.getLocation(), 3, 5, PotionEffectType.WITHER, 20, 2);
/*  85 */           ExplosionDMGPotion(e.getPlayer(), bb.getLocation(), 3, 0, PotionEffectType.SLOW, 20, 2);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  91 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/*  95 */     switch (CustomData) {
/*     */     case 1: 
/*  97 */       PlayerToggleSneakEvent e = (PlayerToggleSneakEvent)event;
/*  98 */       Location s = e.getPlayer().getLocation();
/*  99 */       e.getPlayer().chat(ChatColor.GREEN + "류진노 켄오 쿠라에!");
/* 100 */       this.po = true;
/* 101 */       Timer t = new Timer();
/* 102 */       t.schedule(new TimerTask()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */           Genji.this.po = false;
/* 107 */           cancel();
/*     */         }
/* 109 */       }, 5000L);
/* 110 */       s.setY(s.getY() + 1.6D);
/* 111 */       Block b = s.getBlock();
/* 112 */       BlockIterator bi = new BlockIterator(e.getPlayer(), 15);
/* 113 */       while (bi.hasNext()) {
/* 114 */         Block bb = bi.next();
/* 115 */         if ((bb.getType().isSolid()) && (bb.getType() != Material.AIR)) break;
/* 116 */         b = bb;
/* 117 */         ExplosionDMGL(e.getPlayer(), b.getLocation(), 2, 20);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 122 */       Location l = b.getLocation();
/* 123 */       l.setPitch(s.getPitch());
/* 124 */       l.setYaw(s.getYaw());
/* 125 */       e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
/* 126 */       e.getPlayer().teleport(l);
/* 127 */       e.getPlayer().getWorld().strikeLightningEffect(l);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Genji.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */