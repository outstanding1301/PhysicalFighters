/*    */ package Physical.Fighters.MainModule;
/*    */ 
/*    */ import Physical.Fighters.MinerModule.CommandInterface;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.LinkedList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.PluginCommand;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CommandManager implements CommandExecutor
/*    */ {
/* 15 */   private LinkedList<CommandInterface> CommandEventHandler = new LinkedList();
/*    */   
/*    */   public CommandManager(PhysicalFighters va) {
/* 18 */     va.getCommand("va").setExecutor(this);
/* 19 */     va.getCommand("showmethemoney").setExecutor(this);
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] data)
/*    */   {
/* 24 */     if (command.getName().equals("va")) {
/* 25 */       if (data.length != 0) {
/* 26 */         for (CommandInterface handler : this.CommandEventHandler) {
/* 27 */           if (handler.onCommandEvent(sender, command, label, data)) {
/* 28 */             return true;
/*    */           }
/*    */         }
/*    */       }
/*    */       else {
/* 33 */         sender.sendMessage(ChatColor.GREEN + "명령어 목록");
/* 34 */         sender.sendMessage(ChatColor.YELLOW + "[1]/va start : " + ChatColor.WHITE + "게임을 시작시킵니다.");
/* 35 */         sender.sendMessage(ChatColor.YELLOW + "[2]/va stop : " + ChatColor.WHITE + "게임을 중지시킵니다.");
/* 36 */         sender.sendMessage(ChatColor.YELLOW + "[3]/va help : " + ChatColor.WHITE + "능력을 확인합니다.");
/* 37 */         sender.sendMessage(ChatColor.YELLOW + "[4]/va ob : " + ChatColor.WHITE + "옵저버 설정을 합니다.");
/* 38 */         sender.sendMessage(ChatColor.YELLOW + "[5]/va uti : " + ChatColor.WHITE + "유틸리티 명령 목록을 보여줍니다.");
/* 39 */         sender.sendMessage(ChatColor.YELLOW + "[6]/va debug : " + ChatColor.WHITE + "오류 방어 명령 목록을 보여줍니다.");
/* 40 */         sender.sendMessage(ChatColor.YELLOW + "[7]/va go : " + ChatColor.WHITE + "무적시간을 스킵합니다.");
/* 41 */         sender.sendMessage(ChatColor.YELLOW + "[8]/va inv : " + ChatColor.WHITE + "무적으로 만듭니다.");
/* 42 */         sender.sendMessage(ChatColor.YELLOW + "[9]/va hung : " + ChatColor.WHITE + "배고픔을 설정합니다.");
/* 43 */         sender.sendMessage(ChatColor.YELLOW + "[0]/va dura : " + ChatColor.WHITE + "내구도무한을 설정합니다.");
/* 44 */         return true;
/*    */       }
/*    */     }
/* 47 */     if ((command.getName().equals("showmethemoney")) && 
/* 48 */       (sender.isOp()) && ((sender instanceof Player))) {
/* 49 */       Player p = (Player)sender;
/* 50 */       p.setLevel(p.getLevel() + 60);
/* 51 */       p.sendMessage(ChatColor.GREEN + "역시 대출은 램램머니!");
/* 52 */       return true;
/*    */     }
/*    */     
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public void RegisterCommand(CommandInterface EventHandler){
				CommandEventHandler.add(EventHandler);
			}
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MainModule\CommandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */