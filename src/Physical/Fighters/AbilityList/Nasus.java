/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class Nasus extends AbilityBase
/*    */ {
/* 17 */   int stack = 0;
/*    */   
/* 19 */   public Nasus() { InitAbility("나서스", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] { "시작시 괭이를 지급합니다.", "괭이로 흙을 경작할때마다 스택이 1씩 쌓입니다.", "10스택당 괭이에 1의 추가데미지가 생깁니다." });
/* 20 */     InitAbility(3, 0, true, Physical.Fighters.MainModule.AbilityBase.ShowText.Custom_Text);
/* 21 */     EventManager.onEntityDamageByEntity.add(new Physical.Fighters.MinerModule.EventData(this, 0));
/* 22 */     RegisterRightClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 26 */     if (CustomData == 0) {
/* 27 */       EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
/* 28 */       if ((e.getDamager() instanceof Player)) {
/* 29 */         Player p = (Player)e.getDamager();
/* 30 */         if ((PlayerCheck(p)) && 
/* 31 */           (p.getItemInHand().getType() == Material.WOOD_HOE)) {
/* 32 */           int dmg = this.stack / 10;
/* 33 */           e.setDamage(dmg);
/* 34 */           p.getItemInHand().setDurability((short)0);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 39 */     if (CustomData == 1) {
/* 40 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 41 */       if ((e.getPlayer().getItemInHand().getType() == Material.WOOD_HOE) && 
/* 42 */         (PlayerCheck(e.getPlayer())) && 
/* 43 */         (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) && (
/* 44 */         (e.getClickedBlock().getType() == Material.GRASS) || (e.getClickedBlock().getType() == Material.DIRT))) {
/* 45 */         if (this.stack >= 300) {
/* 46 */           e.getPlayer().sendMessage("300스택 이상 쌓을 수 없습니다.");
/*    */         }
/* 48 */         return 0;
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 54 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 58 */     if (CustomData == 0) {
/* 59 */       this.stack += 1;
/* 60 */       GetPlayer().sendMessage(org.bukkit.ChatColor.YELLOW + "스택을 쌓았습니다. (" + this.stack + ")");
/*    */     }
/*    */   }
/*    */   
/*    */   public void A_SetEvent(Player p) {
/* 65 */     p.getInventory().setItem(8, new ItemStack(262, 64));
/* 66 */     p.getInventory().setItem(7, new ItemStack(261, 1));
/*    */   }
/*    */   
/*    */   public void A_ResetEvent(Player p)
/*    */   {
/* 71 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(262, 64) });
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Nasus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */