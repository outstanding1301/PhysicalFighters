package Physical.Fighters.AbilityList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import Physical.Fighters.PhysicalFighters;
import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MinerModule.CommandInterface;
import Physical.Fighters.OtherModule.Vector;


public class Lockdown extends AbilityBase implements CommandInterface {
	private AbilityBase victim;
	private CommandSender sender;
	private String[] data;
	
	public Lockdown(){
		InitAbility("봉인", Type.Active_Continue, Rank.B,
			"특정 플레이어의 능력을 캔슬시키고 1분간 봉인하며 상대의",
			"배고픔 수치를 0으로 만들어 도망치기 어렵게 합니다.",
			"명령어로 작동하며 사용법은 \"/va s 대상닉네임\" 입니다.",
			"자신과 60칸 이내 거리에 있어야 사용할수 있으며 게임 시작후",
			"능력 제한 시간동안은 이 능력을 사용할 수 없습니다.");
		InitAbility(80, 60, true);
		cm.RegisterCommand(this);
	}

	@Override
	public int A_Condition(Event event, int CustomData){
		if(sender instanceof Player && PlayerCheck((Player)sender)){
			Player p = (Player)sender;
			if(!AbilityBase.restrictionTimer.GetTimerRunning()){
				if(Bukkit.getServer().getPlayerExact(data[1]) != null){
					Player pn = Bukkit.getServer().getPlayerExact(data[1]);
					if(p.getName() == pn.getName()){
						p.sendMessage(ChatColor.RED+"자기 자신에게 능력을 사용할수 없습니다.");
						data = null;
						return -1;
					}
					victim = AbilityBase.FindAbility(pn);
					if(victim != null){
						Vector vec = new Vector(p.getLocation());
						if(vec.distance(pn.getLocation()) <= 60){
							data = null;
							return 0;
						}
						else
							p.sendMessage(ChatColor.RED+"대상 플레이어와의 거리가 너무 멉니다.");
					}
					else
						p.sendMessage(ChatColor.RED+"옵저버입니다.");
				}
				else
					p.sendMessage(ChatColor.RED+"존재하지 않는 플레이어입니다.");
			}
			else
				p.sendMessage(ChatColor.RED+"아직 능력을 사용할 수 없습니다.");
		}
		else
			sender.sendMessage(ChatColor.RED+"이 명령은 사용할 수 없습니다.");
		data = null;
		return -1;
	}

	@Override
	public void A_Effect(Event event, int CustomData){}
	
	@Override
	public void	A_DurationStart(){
		Player p = (Player)sender;
		Player pn = victim.GetPlayer();
		p.sendMessage(String.format("%s님의 능력을 1분간 봉인합니다.", pn.getName()));
		pn.sendMessage(String.format(ChatColor.RED+"경고, %s님이 당신에게 Lockdown 능력을 사용했습니다.", p.getName()));
		pn.sendMessage(ChatColor.RED+"지속 효과가 모두 해제되고 1분간 능력 효과가 봉인됩니다.");
		victim.AbilityDTimerCancel();
		victim.AbilityCTimerCancel();
		victim.SetRunAbility(false);
		if(!PhysicalFighters.NoFoodMode)
			victim.GetPlayer().setFoodLevel(0);
	};
	
	@Override
	public void	A_DurationEnd(){
		Player pn = victim.GetPlayer();
		pn.sendMessage(ChatColor.GREEN+"봉인이 해제되었습니다.");
		victim.SetRunAbility(true);
	};

	@Override
	public boolean onCommandEvent(CommandSender sender, Command command,
			String label, String[] data) {
		this.sender = sender;
		this.data = data;
		if(data[0].equalsIgnoreCase("s") && data.length == 2){
			this.AbilityExcute(null);
			return true;
		}
		return false;
	}
}
