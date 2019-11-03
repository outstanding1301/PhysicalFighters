/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.OtherModule.Vector;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Jumper
/*    */   extends AbilityBase
/*    */ {
/*    */   public Jumper()
/*    */   {
/* 23 */     InitAbility("점퍼", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/* 24 */       "최대 40칸 거리를 순간이동 할수 있습니다.", 
/* 25 */       "단, 벽은 통과할수 없고 낙사 데미지도 받습니다.", 
/* 26 */       "자신이 갈 장소의 바닥 블럭을 클릭해야 텔포가 됩니다.", 
/* 27 */       "사용시 유의하세요. 허공엔 사용이 안됩니다!" });
/* 28 */     InitAbility(20, 0, true);
/* 29 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 35 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/* 36 */       Player p = Event.getPlayer();
/* 37 */       Block b = p.getTargetBlock(null, 0);
/* 38 */       Location loc = b.getLocation();
/* 39 */       Location ploc = p.getLocation();
/* 41 */       Vector targetvec; if ((b.getRelative(0, 1, 0).getTypeId() == 0) && (b.getRelative(0, 2, 0).getTypeId() == 0)) {
/* 42 */         targetvec = new Vector(loc.getX(), loc.getY(), loc.getZ());
/*    */       } else
/* 44 */         targetvec = new Vector(loc.getX(), loc.getY(), loc.getZ());
/* 45 */       Vector playervec = new Vector(ploc.getX(), ploc.getY(), ploc.getZ());
/* 46 */       if ((playervec.distance(targetvec) <= 40.0D) && (b.getY() != 0)) {
/* 47 */         return 0;
/*    */       }
/* 49 */       p.sendMessage(String.format(ChatColor.RED + "거리가 너무 멉니다.", new Object[0]));
/*    */     }
/* 51 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 56 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 57 */     Player p = Event.getPlayer();
/* 58 */     Block b = p.getTargetBlock(null, 0);
/* 59 */     Location loc = b.getLocation();
/* 61 */     Vector targetvec; if ((b.getRelative(0, 1, 0).getTypeId() == 0) && (b.getRelative(0, 2, 0).getTypeId() == 0)) {
/* 62 */       targetvec = new Vector(loc.getX(), loc.getY(), loc.getZ());
/*    */     }
/*    */     else {
/* 65 */       targetvec = new Vector(loc.getX(), b.getWorld().getHighestBlockYAt(b.getX(), b.getZ()), loc.getZ());
/*    */     }
/* 67 */     loc.setX(targetvec.getX() + 0.5D);
/* 68 */     loc.setY(targetvec.getY() + 1.0D);
/* 69 */     loc.setZ(targetvec.getZ() + 0.5D);
/* 70 */     loc.setPitch(p.getLocation().getPitch());
/* 71 */     loc.setYaw(p.getLocation().getYaw());
/* 72 */     p.teleport(loc);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Jumper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */