package dtos;

public class EmissionDto {

    float carbonEquivalent;

    boolean success;

    public EmissionDto(float carbonEquivalent, boolean success) {
        this.carbonEquivalent = carbonEquivalent;
        this.success = success;
    }

    public float getCarbonEquivalent() {
        return carbonEquivalent;
    }

    public void setCarbonEquivalent(float carbonEquivalent) {
        this.carbonEquivalent = carbonEquivalent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
