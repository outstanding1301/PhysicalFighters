/*     */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Trap extends AbilityBase
/*     */ {
/*  25 */   public static Block[] trap = new Block[6];
/*  26 */   public static int traps = 0;
/*     */   
/*     */   public Trap() {
/*  29 */     InitAbility("부비트랩", Physical.Fighters.MainModule.AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/*  30 */       "처음 시작시에 소울샌드가 주어집니다. 소울샌드는 버릴 수 없습니다.", 
/*  31 */       "소울샌드는 최대 5개까지 설치가 가능하며, 철괴 왼쪽클릭으로 수동으로 폭발시킬 수 있습니다." });
/*  32 */     InitAbility(0, 0, true);
/*  33 */     RegisterLeftClickEvent();
/*  34 */     EventManager.onBlockPlaceEvent.add(new EventData(this, 1));
/*  35 */     EventManager.onPlayerDropItem.add(new EventData(this, 3));
/*  36 */     EventManager.onPlayerRespawn.add(new EventData(this, 4));
/*  37 */     EventManager.onEntityDeath.add(new EventData(this, 5));
/*  38 */     EventManager.onBlockBreakEvent.add(new EventData(this, 6));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  43 */     switch (CustomData) {
/*     */     case 0: 
/*  45 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  46 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard) {
/*  47 */         return 0;
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  51 */       BlockPlaceEvent Event1 = (BlockPlaceEvent)event;
/*  52 */       if ((PlayerCheck(Event1.getPlayer())) && (Event1.getBlock().getTypeId() == 88)) {
/*  53 */         return 1;
/*     */       }
/*     */       break;
/*     */     case 3: 
/*  57 */       PlayerDropItemEvent Event3 = (PlayerDropItemEvent)event;
/*  58 */       if ((PlayerCheck(Event3.getPlayer())) && 
/*  59 */         (Event3.getItemDrop().getItemStack().getType() == Material.SOUL_SAND)) {
/*  60 */         PlayerInventory inv = Event3.getPlayer().getInventory();
/*  61 */         if (!inv.contains(Material.SOUL_SAND, 2)) {
/*  62 */           return 3;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 4: 
/*  67 */       PlayerRespawnEvent Event4 = (PlayerRespawnEvent)event;
/*  68 */       if (PlayerCheck(Event4.getPlayer()))
/*  69 */         return 4;
/*     */       break;
/*     */     case 5: 
/*  72 */       EntityDeathEvent Event5 = (EntityDeathEvent)event;
/*  73 */       if (PlayerCheck(Event5.getEntity()))
/*  74 */         return 5;
/*     */       break;
/*  76 */     case 6:  BlockBreakEvent Event6 = (BlockBreakEvent)event;
/*  77 */       if ((PlayerCheck(Event6.getPlayer())) && (Event6.getBlock().getTypeId() == 88)) {
/*  78 */         return 6;
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*  83 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  88 */     switch (CustomData) {
/*     */     case 0: 
/*  90 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  91 */       if (traps != 0) {
/*  92 */         if (trap[0] != null)
/*  93 */           trap[0].getWorld().createExplosion(trap[0].getLocation(), 3.0F);
/*  94 */         if (trap[1] != null)
/*  95 */           trap[1].getWorld().createExplosion(trap[1].getLocation(), 3.0F);
/*  96 */         if (trap[2] != null)
/*  97 */           trap[2].getWorld().createExplosion(trap[2].getLocation(), 3.0F);
/*  98 */         if (trap[3] != null)
/*  99 */           trap[3].getWorld().createExplosion(trap[3].getLocation(), 3.0F);
/* 100 */         if (trap[4] != null) {
/* 101 */           trap[4].getWorld().createExplosion(trap[4].getLocation(), 3.0F);
/*     */         }
/* 103 */         Event.getPlayer().sendMessage(String.format(ChatColor.AQUA + "모든 폭발물을 폭발시켰습니다. 터진 폭발물 : " + 
/* 104 */           ChatColor.WHITE + "%d개", new Object[] { Integer.valueOf(traps) }));
/* 105 */         traps = 0;
/* 106 */         for (int i = 0; i < 5; i++) {
/* 107 */           if (trap[i] != null) {
/* 108 */             trap[i] = null;
/*     */           }
/*     */         }
/*     */       } else {
/* 112 */         Event.getPlayer().sendMessage("폭발물이 없습니다. 설치해주세요.");
/*     */       }
/* 114 */       break;
/*     */     case 1: 
/* 116 */       BlockPlaceEvent Event1 = (BlockPlaceEvent)event;
/* 117 */       if (traps < 5) {
/* 118 */         Event1.getPlayer().sendMessage(String.format(ChatColor.AQUA + "폭발물을 설치했습니다. 폭발물 : " + 
/* 119 */           ChatColor.WHITE + "(%d/5)", new Object[] { Integer.valueOf(traps + 1) }));
/* 120 */         traps += 1;
/* 121 */         trap[traps] = Event1.getBlock();
/* 122 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/* 123 */         inv.setItem(8, new ItemStack(88, 1));
/* 124 */       } else { Event1.getPlayer().sendMessage("더 이상 폭발물을 설치할 수 없습니다.");
/* 125 */         Event1.setCancelled(true);
/* 126 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/* 127 */         inv.setItem(8, new ItemStack(88, 1)); }
/* 128 */       break;
/*     */     case 6: 
/* 130 */       BlockBreakEvent Event6 = (BlockBreakEvent)event;
/* 131 */       if (traps >= 0) {
/* 132 */         Event6.getPlayer().sendMessage(String.format(ChatColor.AQUA + "폭발물이 제거되었습니다. 폭발물 : " + 
/* 133 */           ChatColor.WHITE + "(%d/5)", new Object[] { Integer.valueOf(traps - 1) }));
/* 134 */         traps -= 1;
/* 135 */         trap[traps] = Event6.getBlock();
/* 136 */         PlayerInventory inv = Event6.getPlayer().getInventory();
/* 137 */         inv.setItem(8, new ItemStack(88, 1));
/* 138 */       } else { Event6.getPlayer().sendMessage("ERROR");
/* 139 */         Event6.setCancelled(true);
/* 140 */         PlayerInventory inv = Event6.getPlayer().getInventory();
/* 141 */         inv.setItem(8, new ItemStack(88, 1)); }
/* 142 */       break;
/*     */     case 3: 
/* 144 */       PlayerDropItemEvent Event3 = (PlayerDropItemEvent)event;
/* 145 */       Event3.getPlayer().sendMessage(
/* 146 */         ChatColor.RED + "소울샌드는 버릴 수 없습니다.");
/* 147 */       Event3.setCancelled(true);
/* 148 */       break;
/*     */     case 4: 
/* 150 */       PlayerRespawnEvent Event4 = (PlayerRespawnEvent)event;
/* 151 */       Event4.getPlayer().sendMessage(
/* 152 */         ChatColor.GREEN + "소울샌드가 지급됩니다.");
/* 153 */       PlayerInventory inv = Event4.getPlayer().getInventory();
/* 154 */       inv.setItem(8, new ItemStack(Material.SOUL_SAND.getId(), 1));
/* 155 */       break;
/*     */     case 5: 
/* 157 */       EntityDeathEvent Event5 = (EntityDeathEvent)event;
/* 158 */       List<ItemStack> itemlist = Event5.getDrops();
/* 159 */       for (int l = 0; l < itemlist.size(); l++) {
/* 160 */         if (((ItemStack)itemlist.get(l)).getType() == Material.SOUL_SAND) {
/* 161 */           itemlist.remove(l);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 168 */     p.getInventory().setItem(8, new ItemStack(Material.SOUL_SAND.getId(), 1));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Trap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */