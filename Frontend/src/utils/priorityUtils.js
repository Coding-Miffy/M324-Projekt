export const priorityToColor = (priority) => {
    switch (priority) {
        case "HIGH": return "#ff0000";
        case "MEDIUM": return "#ffff00";
        case "LOW": return "#00ff00";
        default: return "#ffffff";
    }
};

export const priorityToLabel = (priority) => {
    switch (priority) {
        case "HIGH": return "Hoch";
        case "MEDIUM": return "Mittel";
        case "LOW": return "Tief";
        default: return priority;
    }
};