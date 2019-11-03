/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import java.util.Map;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class ReverseAlchemy extends AbilityBase
/*    */ {
/*    */   public ReverseAlchemy()
/*    */   {
/* 20 */     InitAbility("반 연금술", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.C, new String[] {
/* 21 */       "제련된 금을 철괴와 1:1, 다이아몬드와 3:1 비율로 교환합니다.", 
/* 22 */       "철은 왼클릭, 다이아몬드는 오른클릭으로 사용합니다.", 
/* 23 */       "비록 철괴로 능력을 작동시키지만 금괴는 갖고 있어야 합니다.", 
/* 24 */       "금괴를 들고 오른클릭시 금괴를 하나 소모하여 자신의 체력과", 
/* 25 */       "포만감을 모두 채워줍니다." });
/* 26 */     InitAbility(2, 0, true, AbilityBase.ShowText.No_Text);
/* 27 */     RegisterLeftClickEvent();
/* 28 */     RegisterRightClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 33 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 34 */     if (PlayerCheck(Event.getPlayer())) {
/* 35 */       PlayerInventory inv = Event.getPlayer().getInventory();
/* 36 */       if (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) {
/* 37 */         switch (CustomData) {
/*    */         case 0: 
/* 39 */           if (inv.contains(Material.GOLD_INGOT, 1)) {
/* 40 */             return CustomData;
/*    */           }
/*    */           break;
/*    */         case 1: 
/* 44 */           if (inv.contains(Material.GOLD_INGOT, 3))
/* 45 */             return CustomData;
/*    */           break;
/*    */         }
/* 48 */         Event.getPlayer().sendMessage(ChatColor.RED + "금괴가 부족합니다.");
/*    */       }
/* 50 */       else if ((ItemCheck(Material.GOLD_INGOT.getId())) && 
/* 51 */         (inv.contains(Material.GOLD_INGOT, 1)) && (CustomData == 1)) {
/* 52 */         return 2;
/*    */       }
/*    */     }
/*    */     
/* 56 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 62 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 63 */     switch (CustomData) {
/*    */     case 0: 
/* 65 */       Event.getPlayer().getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT.getId(), 1) });
/* 66 */       GiveItem(Event.getPlayer(), Material.IRON_INGOT, 1);
/* 67 */       break;
/*    */     case 1: 
/* 69 */       Event.getPlayer().getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT.getId(), 3) });
/* 70 */       GiveItem(Event.getPlayer(), Material.DIAMOND, 1);
/* 71 */       break;
/*    */     case 2: 
/* 73 */       Event.getPlayer().getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT.getId(), 1) });
/* 74 */       Event.getPlayer().setHealth(20);
/* 75 */       Event.getPlayer().setFoodLevel(20);
/* 76 */       Event.getPlayer().setSaturation(5.0F);
/*    */     }
/*    */     
/* 79 */     Event.getPlayer().updateInventory();
/*    */   }
/*    */   
/*    */   private void GiveItem(Player p, Material item, int amount) {
/* 83 */     PlayerInventory inv = p.getInventory();
/* 84 */     Map<Integer, ItemStack> overflow = inv.addItem(new ItemStack[] { new ItemStack(item, amount) });
/* 85 */     for (ItemStack is : overflow.values())
/*    */     {
/* 87 */       p.getWorld().dropItemNaturally(p.getLocation(), is);
/* 88 */       p.sendMessage(ChatColor.RED + "경고, 인벤토리 공간이 부족합니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\ReverseAlchemy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */