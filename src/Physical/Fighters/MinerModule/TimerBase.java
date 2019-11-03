package Physical.Fighters.MinerModule;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimerBase {
	private Timer timer;
	private boolean Running = false;
	private boolean ReverseTimer = false;
	private int Count = 0;
	private int MaxCount = 0;
	private boolean Infinity = false;
	
	public abstract void EventStartTimer();
	public abstract void EventRunningTimer(int count);
	public abstract void EventEndTimer();
	public void FinalEventEndTimer() {}
	
	public final void		SetTimerData(int MaxCount, boolean Reverse){
		this.MaxCount = MaxCount; 
		this.ReverseTimer = Reverse; 
	}
	public final int			GetCount()			{return Count;}
	public final void		SetCount(int c)		{Count = c;}
	public final boolean	GetTimerRunning()	{return Running;}
	
	public final void StartTimer()				{StartTimer(MaxCount, ReverseTimer);}
	public final void StartTimer(int MaxCount)	{StartTimer(MaxCount, false);}
	public final void StartTimer(int MaxCount, boolean Reverse){StartTimer(MaxCount, Reverse, 1000);}
	public final void StartTimer(int MaxCount, boolean Reverse, int period){
		timer = new Timer();
		timer.schedule(new CustomTimerTask(), 0, period);
		Running = true;
		SetTimerData(MaxCount, Reverse);
		Count = 0;
		if(Reverse)
			Count = MaxCount;
		if(MaxCount == -1)
			Infinity = true;
		EventStartTimer();
	}
	
	public final void EndTimer(){
		StopTimer();
		EventEndTimer();
	}
	
	public final void StopTimer(){
		if(timer != null)
			timer.cancel();
		Count = 0;
		Running = false;
		FinalEventEndTimer();
	}
	
	private final class CustomTimerTask extends TimerTask{
		@Override
		public void run(){
			EventRunningTimer(Count);
			if(!Infinity){
				if(ReverseTimer){
					if(Count <= 0){
						EndTimer();
						return;
					}
					--Count;
				} else {
					if(Count >= MaxCount){
						EndTimer();
						return;
					}
					++Count;
				}
			}
		}
	}
}
