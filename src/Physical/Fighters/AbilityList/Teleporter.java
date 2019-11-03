/*     */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Teleporter extends AbilityBase
/*     */ {
/*  25 */   static String signName = null;
/*  26 */   static Location signLoc = null;
/*     */   
/*     */   public Teleporter() {
/*  29 */     InitAbility("소환술사", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, 
/*  30 */       AbilityBase.Rank.A, new String[] {
/*  31 */       "표지판을 설치하고, 플레이어의 이름을 적은 뒤, 철괴를 휘두르면", 
/*  32 */       "이름이 적힌 플레이어는 표지판으로 이동합니다.", 
/*  33 */       "자기 자신의 이름도 적을 수 있습니다. [표지판을 벽에 설치하면 적용되지 않습니다]" });
/*  34 */     InitAbility(100, 0, true);
/*  35 */     RegisterLeftClickEvent();
/*  36 */     EventManager.onSignChangeEvent.add(new EventData(this, 1));
/*  37 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  38 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*  39 */     EventManager.onBlockBreakEvent.add(new EventData(this, 4));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  43 */     switch (CustomData) {
/*     */     case 0: 
/*  45 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  46 */       if ((PlayerCheck(Event.getPlayer())) && !EventManager.DamageGuard && 
/*  47 */         (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/*  48 */         if ((signName != null) && (signLoc != null)) {
/*  49 */           return 0;
/*     */         }
/*  51 */         Event.getPlayer().sendMessage(
/*  52 */           ChatColor.RED + "표지판을 설치하셔야 합니다.");
/*     */       }
/*     */       
/*  55 */       break;
/*     */     case 1: 
/*  57 */       SignChangeEvent event2 = (SignChangeEvent)event;
/*  58 */       if (PlayerCheck(event2.getPlayer()) && !EventManager.DamageGuard) {
/*  59 */         if (event2.getBlock().getType() == Material.SIGN_POST) {
/*  60 */           if ((!event2.getLine(0).isEmpty()) && 
/*  61 */             (Bukkit.getPlayer(event2.getLine(0)).isOnline())) {
/*  62 */             signName = 
/*  63 */               Bukkit.getPlayer(event2.getLine(0)).getName();
/*  64 */             signLoc = event2.getBlock().getLocation();
/*  65 */             event2.getPlayer().sendMessage(
/*  66 */               ChatColor.LIGHT_PURPLE + "철괴를 휘두르면 " + 
/*  67 */               ChatColor.WHITE + signName + 
/*  68 */               ChatColor.LIGHT_PURPLE + 
/*  69 */               "님은 이곳으로 텔레포트됩니다.");
/*     */           }
/*     */           
/*     */         }
/*  73 */         else if ((event2.getBlock().getType() == Material.SIGN) && 
/*  74 */           (PlayerCheck(event2.getPlayer()))) {
/*  75 */           event2.getPlayer().sendMessage(
/*  76 */             ChatColor.RED + "표지판은 바닥에 세워야합니다.");
/*     */         }
/*     */       }
/*  79 */       break;
/*     */     case 2: 
/*  81 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  82 */       if (PlayerCheck(Event2.getPlayer()))
/*  83 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  86 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  87 */       if (PlayerCheck(Event3.getEntity()))
/*  88 */         return 3;
/*     */       break;
/*     */     case 4: 
/*  91 */       BlockBreakEvent Event4 = (BlockBreakEvent)event;
/*  92 */       if ((signLoc != null) && (Event4.getBlock().getLocation() == signLoc)) {
/*  93 */         signName = null;
/*  94 */         signLoc = null;
/*  95 */         GetPlayer().sendMessage(ChatColor.RED + "표지판이 제거되었습니다.");
/*     */       }
/*     */       break;
/*     */     }
/*  99 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/* 103 */     switch (CustomData) {
/*     */     case 0: 
/* 105 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 106 */       Player p = Event.getPlayer();
/* 107 */       Player t = Bukkit.getPlayer(signName);
/* 108 */       t.sendMessage(ChatColor.RED + "소환술사에 의해 당신은 표지판으로 이동합니다.");
/* 109 */       t.teleport(signLoc);
/* 110 */       p.sendMessage(ChatColor.GREEN + 
/* 111 */         "성공적으로 소환을 마쳤습니다. 100초간 능력을 사용하지 못합니다.");
/* 112 */       signLoc.getBlock().breakNaturally();
/* 113 */       signName = null;
/* 114 */       signLoc = null;
/* 115 */       break;
/*     */     case 2: 
/* 117 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 118 */       Event2.getPlayer().sendMessage(
/* 119 */         ChatColor.GREEN + "이전에 소유했던 화살은 모두 소멸하며 다시 지급됩니다.");
/* 120 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 121 */       inv.remove(new ItemStack(323, 64));
/* 122 */       inv.setItem(8, new ItemStack(323, 3));
/* 123 */       break;
/*     */     case 3: 
/* 125 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 126 */       List<ItemStack> itemlist = Event3.getDrops();
/* 127 */       for (int l = 0; l < itemlist.size(); l++) {
/* 128 */         if (((ItemStack)itemlist.get(l)).getType() == Material.ARROW)
/* 129 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 135 */     p.getInventory().setItem(8, new ItemStack(323, 3));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p) {
/* 139 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(323) });
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Teleporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */