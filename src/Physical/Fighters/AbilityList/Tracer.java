/*     */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*     */ import org.bukkit.util.BlockIterator;
/*     */ 
/*     */ public class Tracer extends AbilityBase
/*     */ {
/*  27 */   int core = 3;
/*  28 */   int aaa = 0;
/*  29 */   LinkedList<Location> L = new LinkedList<Location>();
/*  30 */   LinkedList<Integer> H = new LinkedList<Integer>();
/*     */   
/*  32 */   public Tracer() { if (PhysicalFighters.SRankUsed) {
/*  33 */       InitAbility(
/*  34 */         "트레이서", 
/*  35 */         Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, 
/*  36 */         AbilityBase.Rank.S, 
/*  37 */         new String[] {
/*  38 */         "1.철괴를 들고 쉬프트를 누르면 바라보는 방향으로 짧게 순간이동합니다.", 
/*  39 */         "- 5초마다 코어가 하나씩 충전되며, 3개까지 충전됩니다.", 
/*  40 */         "2.철괴를 들고 우클릭시 자신의 모든 상태를 4초전으로 돌립니다. (쿨타임:40초)", 
/*  41 */         "*낙하데미지를 받지않습니다." });
/*  42 */       InitAbility(40, 0, true);
/*  43 */       EventManager.onPlayerToggleSneakEvent.add(new EventData(this));
/*  44 */       RegisterRightClickEvent();
/*  45 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*  46 */       PhysicalFighters.TracerTimer = new Timer();
/*  47 */       PhysicalFighters.TracerTimer.schedule(new TimerTask()
/*     */       {
/*     */         public void run()
/*     */         {
/*  51 */           if (Tracer.this.hasPlayer()) {
/*  52 */             if (Tracer.this.core < 3) {
/*  53 */               Tracer.this.aaa += 1;
/*  54 */               if (Tracer.this.aaa == 5) {
/*  55 */                 Tracer.this.core += 1;
/*  56 */                 String s = "";
/*  57 */                 for (int i = 0; i < Tracer.this.core; i++) {
/*  58 */                   s = s + "▶";
/*     */                 }
/*     */                 
/*  61 */                 Tracer.this.GetPlayer().sendMessage(ChatColor.AQUA + s);
/*  62 */                 Tracer.this.aaa = 0;
/*     */               }
/*     */             }
/*  65 */             if (Tracer.this.GetPlayer().isOnline()) {
/*  66 */               Tracer.this.L.add(Tracer.this.GetPlayer().getLocation());
/*  67 */               Tracer.this.H.add(GetPlayer().getHealth());
/*  68 */               if (Tracer.this.L.size() > 4) {
/*  69 */                 Tracer.this.L.removeFirst();
/*     */               }
/*  71 */               if (Tracer.this.H.size() > 4) {
/*  72 */                 Tracer.this.H.removeFirst();
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*  79 */       }, 1000L, 1000L);
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  85 */     switch (CustomData) {
/*     */     case 0: 
/*  87 */       PlayerToggleSneakEvent e = (PlayerToggleSneakEvent)event;
/*  88 */       if ((PlayerCheck(e.getPlayer())) && (e.isSneaking())) {
/*  89 */         if (this.core >= 1) {
/*  90 */           Location s = e.getPlayer().getLocation();
/*  91 */           s.setY(s.getY() + 1.6D);
/*  92 */           Block b = s.getBlock();
/*  93 */           BlockIterator bi = new BlockIterator(e.getPlayer(), 5);
/*  94 */           while (bi.hasNext()) {
/*  95 */             Block bb = bi.next();
/*  96 */             if ((bb.getType().isSolid()) && (bb.getType() != Material.AIR)) break;
/*  97 */             b = bb;
/*     */           }
/*     */           
/*     */ 
/* 101 */           Location l = b.getLocation();
/* 102 */           l.setPitch(s.getPitch());
/* 103 */           l.setYaw(s.getYaw());
/* 104 */           e.getPlayer().teleport(l);
/* 105 */           e.getPlayer().playEffect(
/* 106 */             s, 
/* 107 */             Effect.ENDER_SIGNAL, 0);
/* 108 */           e.getPlayer().playEffect(
/* 109 */             l, 
/* 110 */             Effect.ENDER_SIGNAL, 0);
/* 111 */           e.getPlayer().playSound(
/* 112 */             e.getPlayer().getLocation(), 
/* 113 */             Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
/* 114 */           this.core -= 1;
/* 115 */           String ss = "";
/* 116 */           for (int i = 0; i <= this.core; i++) {
/* 117 */             ss = ss + "▶";
/*     */           }
/* 119 */           GetPlayer().sendMessage(ChatColor.AQUA + ss);
/*     */         } else {
/* 121 */           e.getPlayer().sendMessage(ChatColor.BLUE + "코어가 부족합니다! 5초마다 자동 생성됩니다.");
/*     */         }
/*     */       }
/* 124 */       break;
/*     */     case 1: 
/* 126 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 127 */       if ((PlayerCheck(Event2.getPlayer())) && 
/* 128 */         (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 129 */         return 2;
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/* 135 */     if (CustomData == 3) {
/* 136 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/* 137 */       if ((PlayerCheck(Event2.getEntity())) && 
/* 138 */         (Event2.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL)) {
/* 139 */         Event2.setCancelled(true);
/* 140 */         GetPlayer().sendMessage(
/* 141 */           ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/*     */       }
/*     */     }
/*     */     
/* 145 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/* 149 */     switch (CustomData)
/*     */     {
/*     */     case 2: 
/* 152 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 153 */       if ((!this.L.isEmpty()) && (this.L.getFirst() != null) && (!this.H.isEmpty())) {
/* 154 */         Location l = e.getPlayer().getLocation();
/* 155 */         Location tol = (Location)this.L.getFirst();
/* 156 */         l.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 1);
/* 157 */         l.getWorld().playSound(l, Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
/* 158 */         e.getPlayer().teleport((Location)this.L.getFirst());
/* 159 */         e.getPlayer().setHealth((this.H.getFirst()));
/* 160 */         tol.getWorld().playEffect(tol, Effect.ENDER_SIGNAL, 1);
/* 161 */         e.getPlayer().sendMessage("능력을 사용하여 4초전으로 되돌립니다.");
/*     */       }
/*     */       else {
/* 164 */         e.getPlayer().sendMessage("타이머로 좌표를 불러오는데 오류가 발생했다고 염료한테 말하세요.");
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Tracer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */