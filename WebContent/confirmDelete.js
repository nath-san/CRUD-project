function confirmation() {
	var answer = confirm("Are you sure you want to delete this person?")
	if (answer){
		return true;
	}
	else{
		return false;
	}
}