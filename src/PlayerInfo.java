class PlayerInfo {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public PlayerInfo(long id, String name) {
        this.id = id;
        this.name = name;

    }

    // Add getters and setters as needed.
    public long getId() {

        return id;
    }

    public String getName() {

        return name;
    }
}

