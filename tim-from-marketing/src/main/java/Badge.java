class Badge {
    public String print(Integer id, String name, String department) {
        if (department == null && id ==null){
            return name+" - "+"OWNER";
        }
        else if (department == null && id != null){
            return "["+id+"]"+" - "+name+" - "+"OWNER";
        }
        else if(id == null){
            return name+" - "+department.toUpperCase();
        }
        else {
            return "["+id+"]"+" - "+name+" - "+department.toUpperCase();
        }
    }
}
