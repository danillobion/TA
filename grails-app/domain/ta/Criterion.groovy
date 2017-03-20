package ta

class Criterion {
    String description
    
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	static constraints = {
        description unique: true, blank: false, nullable: false
    }

    public Criterion(String description){
        this.description = description
    }
	
	public String toString(){
		return "Decription: " + this.description
	}
}
