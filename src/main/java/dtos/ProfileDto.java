package dtos;

import entities.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * A DTO for the {@link Profile} entity
 */
public class ProfileDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private String email;
    @Size(max = 45)
    @NotNull
    private String name;
    @NotNull
    private UserDto user;
    private Set<JourneyDto> journeys = new LinkedHashSet<>();

    public ProfileDto(Integer id, String email, String name, UserDto user, Set<JourneyDto> journeys) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.user = user;
        this.journeys = journeys;
    }

    public ProfileDto(String email, String name, UserDto user) {
        this.email = email;
        this.name = name;
        this.user = user;
    }

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.name = profile.getName();
        this.user = new UserDto(profile.getUser());
        if(profile.getJourneys() != null){
            for (Journey journey : profile.getJourneys()){
                this.journeys.add(new JourneyDto(journey));
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserDto getUser() {
        return user;
    }

    public Set<JourneyDto> getJourneys() {
        return journeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto entity = (ProfileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.journeys, entity.journeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, user, journeys);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "name = " + name + ", " +
                "user = " + user + ", " +
                "journeys = " + journeys + ")";
    }

    /**
     * A DTO for the {@link User} entity
     */
    public static class UserDto implements Serializable {
        private Integer id;
        @Size(max = 25)
        @NotNull
        private String userName;
        @Size(max = 255)
        private String userPass;
        private Set<RoleDto> roles = new LinkedHashSet<>();

        public UserDto(Integer id, String userName, String userPass, Set<RoleDto> roles) {
            this.id = id;
            this.userName = userName;
            this.userPass = userPass;
            this.roles = roles;
        }

        public UserDto(User user) {
            this.id = user.getId();
            this.userName = user.getUserName();
            this.userPass = user.getUserPass();
            for (Role role : user.getRoles()){
                this.roles.add(new RoleDto(role.getId(), role.getRoleName()));
            }
        }

        public UserDto(String userName, String userPass) {
            this.userName = userName;
            this.userPass = userPass;
        }

        public List<String> getRolesAsStrings() {
            if (roles.isEmpty()) {
                return null;
            }
            List<String> rolesAsStrings = new ArrayList<>();
            roles.forEach((role) -> {
                rolesAsStrings.add(role.getRoleName());
            });
            return rolesAsStrings;
        }

        public Integer getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPass() {
            return userPass;
        }

        public Set<RoleDto> getRoles() {
            return roles;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserDto entity = (UserDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.userName, entity.userName) &&
                    Objects.equals(this.userPass, entity.userPass) &&
                    Objects.equals(this.roles, entity.roles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, userName, userPass, roles);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "userName = " + userName + ", " +
                    "userPass = " + userPass + ", " +
                    "roles = " + roles + ")";
        }

        /**
         * A DTO for the {@link Role} entity
         */
        public static class RoleDto implements Serializable {
            private Integer id;
            @Size(max = 20)
            @NotNull
            private String roleName;

            public RoleDto(Integer id, String roleName) {
                this.id = id;
                this.roleName = roleName;
            }

            public RoleDto() {
            }

            public Integer getId() {
                return id;
            }

            public String getRoleName() {
                return roleName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RoleDto entity = (RoleDto) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.roleName, entity.roleName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, roleName);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "roleName = " + roleName + ")";
            }
        }
    }

    /**
     * A DTO for the {@link Journey} entity
     */
    public static class JourneyDto implements Serializable {
        private Integer id;
        @Size(max = 45)
        @NotNull
        private String name;
        @NotNull
        private String date;
        @NotNull
        private Float totalEmission;
        @NotNull
        private Float totalDistance;
        @NotNull
        private Float totalCost;
        @NotNull
        private JourneyTypeDto journeyType;

        private Profile profile;
        private Set<TripDto> trips = new LinkedHashSet<>();

        public JourneyDto(Integer id, String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost, JourneyTypeDto journeyType) {
            this.id = id;
            this.name = name;
            this.date = date.toString();
            this.totalEmission = totalEmission;
            this.totalDistance = totalDistance;
            this.totalCost = totalCost;
            this.journeyType = journeyType;

        }

        public JourneyDto(Journey journey){
            this.id = journey.getId();
            this.name = journey.getName();
            this.date = journey.getDate().toString();
            this.totalEmission = journey.getTotalEmission();
            this.totalDistance = journey.getTotalDistance();
            this.totalCost = journey.getTotalCost();
            this.profile = journey.getProfile();
            this.journeyType = new JourneyTypeDto(journey.getJourneyType().getId(), journey.getJourneyType().getName());
            if(journey.getTrips() != null){
                for(Trip trip : journey.getTrips()){
                    this.trips.add(new TripDto(trip));
                }
            }
        }

        public JourneyDto() {
        }

        public static List<ProfileDto.JourneyDto> getDtos(List<Journey> journeyList) {
            List<ProfileDto.JourneyDto> journeyDtos = new ArrayList();
            journeyList.forEach(journey -> journeyDtos.add(new ProfileDto.JourneyDto(journey)));
            return journeyDtos;
        }


        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public Float getTotalEmission() {
            return totalEmission;
        }

        public Float getTotalDistance() {
            return totalDistance;
        }

        public Float getTotalCost() {
            return totalCost;
        }

        public JourneyTypeDto getJourneyType() {
            return journeyType;
        }

        public Set<TripDto> getTrips() {
            return trips;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JourneyDto entity = (JourneyDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.date, entity.date) &&
                    Objects.equals(this.totalEmission, entity.totalEmission) &&
                    Objects.equals(this.totalDistance, entity.totalDistance) &&
                    Objects.equals(this.totalCost, entity.totalCost) &&
                    Objects.equals(this.journeyType, entity.journeyType) &&
                    Objects.equals(this.trips, entity.trips);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, date, totalEmission, totalDistance, totalCost, journeyType, trips);
        }

        @Override
        public String toString() {
            return "JourneyDto{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", date='" + date + '\'' +
                    ", totalEmission=" + totalEmission +
                    ", totalDistance=" + totalDistance +
                    ", totalCost=" + totalCost +
                    ", journeyType=" + journeyType +
                    ", profile=" + profile +
                    ", trips=" + trips +
                    '}';
        }

        /**
         * A DTO for the {@link JourneyType} entity
         */
        public static class JourneyTypeDto implements Serializable {
            private Integer id;
            @Size(max = 45)
            @NotNull
            private String name;

            public JourneyTypeDto(Integer id, String name) {
                this.id = id;
                this.name = name;
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                JourneyTypeDto entity = (JourneyTypeDto) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.name, entity.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, name);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "name = " + name + ")";
            }
        }

        /**
         * A DTO for the {@link Trip} entity
         */
        public static class TripDto implements Serializable {
            private Integer id;
            @NotNull
            private Float distance;
            @NotNull
            private Float emission;
            @NotNull
            private Float cost;
            @NotNull
            private FuelDto fuel;
            @NotNull
            private TransportationDto transportation;

            public TripDto(Integer id, Float distance, Float emission, Float cost, FuelDto fuel, TransportationDto transportation) {
                this.id = id;
                this.distance = distance;
                this.emission = emission;
                this.cost = cost;
                this.fuel = fuel;
                this.transportation = transportation;
            }

            public TripDto(Trip trip){
                this.id = trip.getId();
                this.distance = trip.getDistance();
                this.emission = trip.getEmission();
                this.cost = trip.getCost();
                this.fuel = new FuelDto(trip.getFuel().getId(), trip.getFuel().getName());
                this.transportation = new TransportationDto(trip.getTransportation().getId(), trip.getTransportation().getName());
            }

            public Integer getId() {
                return id;
            }

            public Float getDistance() {
                return distance;
            }

            public Float getEmission() {
                return emission;
            }

            public Float getCost() {
                return cost;
            }

            public FuelDto getFuel() {
                return fuel;
            }

            public TransportationDto getTransportation() {
                return transportation;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TripDto entity = (TripDto) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.distance, entity.distance) &&
                        Objects.equals(this.emission, entity.emission) &&
                        Objects.equals(this.cost, entity.cost) &&
                        Objects.equals(this.fuel, entity.fuel) &&
                        Objects.equals(this.transportation, entity.transportation);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, distance, emission, cost, fuel, transportation);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "distance = " + distance + ", " +
                        "emission = " + emission + ", " +
                        "cost = " + cost + ", " +
                        "fuel = " + fuel + ", " +
                        "transportation = " + transportation + ")";
            }

            /**
             * A DTO for the {@link Fuel} entity
             */
            public static class FuelDto implements Serializable {
                private Integer id;
                @Size(max = 45)
                @NotNull
                private String name;

                public FuelDto(Integer id, String name) {
                    this.id = id;
                    this.name = name;
                }

                public Integer getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    FuelDto entity = (FuelDto) o;
                    return Objects.equals(this.id, entity.id) &&
                            Objects.equals(this.name, entity.name);
                }

                @Override
                public int hashCode() {
                    return Objects.hash(id, name);
                }

                @Override
                public String toString() {
                    return getClass().getSimpleName() + "(" +
                            "id = " + id + ", " +
                            "name = " + name + ")";
                }
            }

            /**
             * A DTO for the {@link Transportation} entity
             */
            public static class TransportationDto implements Serializable {
                private Integer id;
                @Size(max = 45)
                @NotNull
                private String name;

                public TransportationDto(Integer id, String name) {
                    this.id = id;
                    this.name = name;
                }

                public Integer getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    TransportationDto entity = (TransportationDto) o;
                    return Objects.equals(this.id, entity.id) &&
                            Objects.equals(this.name, entity.name);
                }

                @Override
                public int hashCode() {
                    return Objects.hash(id, name);
                }

                @Override
                public String toString() {
                    return getClass().getSimpleName() + "(" +
                            "id = " + id + ", " +
                            "name = " + name + ")";
                }
            }
        }
    }
}