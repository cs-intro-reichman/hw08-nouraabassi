
class PlayList {
    private Track[] tracks;   
    private int maxSize;     
    private int size;        
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }
public int getMaxSize() {
        return maxSize;
    }
    public int getSize() {
        return size;
    } 
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    } 
    public boolean add(Track track) {
        if (this.size < this.maxSize) {
			this.tracks[this.size] = track;
			this.size++;
			return true;
		}
        return false;
    }

    public String toString() {
        String input = "";
		for(int i = 0; i < this.size; i++) {
			input += "/n";
			input += this.tracks[i].toString();
		}
        return input;
    }
     public void removeLast() {
        if (this.size != 0){
			this.tracks[this.size-1] = null;
			this.size--;
		}
    }
    
    public int totalDuration() {
		int count = 0;
		for (int i = 0; i<this.size; i++) {
			count += this.tracks[i].getDuration();
		}
        return count;
    }

    public int indexOf(String title) {
        title.toLowerCase();
		String ch;
		for (int i = 0; i<this.size; i++) {
			ch = this.tracks[i].getTitle();
			ch.toLowerCase();
			if (ch.equals(title)) {
				return i;
			}
		}
        return -1;
    }


    public boolean add(int i, Track track) {
        if (i < 0 || i > this.size) {
			return false;
		}
		if (this.size < this.maxSize) {
			if (i == this.size) {
				this.tracks[this.size] = track;
			} else {
				for (int j = this.size; j >= i; j--) {

                    
					this.tracks[j+1] = this.tracks[j];
				}
				
				this.tracks[i] = track;
			}
			this.size++;
			return true;
		}			
        return false;
    }
    public void remove(int i) {
        if (i >= 0 && i < this.size && this.size != 0) {
			if (i == this.size) {
				this.tracks[this.size] = null;
			} else {
				for (int j = i; j < this.size; j++) {
			
					this.tracks[j] = this.tracks[j+1];
				}
	
			}
			this.size--;		
		}
	}

    
    public void remove(String title) {
        int index = indexOf(title);
		if (index >= 0 && index <= this.size) {
			for (int j = index; j < this.size; j++) {
				
				this.tracks[j] = this.tracks[j+1];
			}
			size--;
		}
			
    }

    public void removeFirst() {
        remove(0);
    }
     public void add(PlayList other) {
        if ((this.size + other.size) <= this.maxSize) {
			for (int i = 0; i < other.size; i++) {
				add(other.getTrack(i));
			}
		}
    }

    private int minIndex(int start) {
        int minimum = this.tracks[start].getDuration();
		int index = 0;
		if (start >= 0 && start <= this.size) {
			for (int i = start; i < this.size; i++) {
				if (this.tracks[i].getDuration() < minimum) {
					minimum = this.tracks[i].getDuration();
					index = i;
				}
			}
		}
        return index;
    }

    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }


    public void sortedInPlace() {
        Track temp=this.tracks[0];
        int min=0;
        for(int i=0; i<this.size; i++)
        {
            if (i<minIndex(i))
            {
                min=minIndex(i);
                temp=this.tracks[i];
                this.tracks[i]=this.tracks[min];
                this.tracks[min]=temp;
            }
        }
    }
}
