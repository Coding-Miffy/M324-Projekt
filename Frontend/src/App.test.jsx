import { describe, test, expect, beforeEach, vi } from "vitest";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import App from "./App";

beforeEach(() => {
  window.fetch = vi.fn((url, options) => {
    if (options?.method === "POST") {
      return Promise.resolve({
        json: () => Promise.resolve({ taskdescription: "", color: "#ffffff", tag: null, priority: "LOW" }),
      });
    }
    return Promise.resolve({
      json: () => Promise.resolve([]),
    });
  });
});

// Color-picker tests
describe("Todo color feature", () => {
  test("shows color picker with default value", () => {
    render(<App/>);

    const colorInput = screen.getByLabelText("Farbe auswaehlen:");

    expect(colorInput).toBeInTheDocument();
    expect(colorInput.value).toBe("#ffffff");
  });

  test("submits selected color when creating todo", async () => {
    render(<App/>);

    fireEvent.change(screen.getByLabelText("Neues Todo anlegen:"), {
      target: {value: "Frontend Test Task"},
    });

    fireEvent.change(screen.getByLabelText("Farbe auswaehlen:"), {
      target: {value: "#ff0000"},
    });

    fireEvent.click(screen.getByText("Absenden"));

    await waitFor(() => {
      expect(window.fetch).toHaveBeenCalledWith(
          "http://localhost:8080/api/v1/tasks",
          expect.objectContaining({
            method: "POST",
            body: JSON.stringify({
              taskdescription: "Frontend Test Task",
              color: "#ff0000",
              tag: null,
              priority: "LOW",
            }),
          }),
      );
    });
  });

    test("resets color picker to default after submitting", async () => {
      render(<App/>);

      const textInput = screen.getByLabelText("Neues Todo anlegen:");
      const colorInput = screen.getByLabelText("Farbe auswaehlen:");

      fireEvent.change(textInput, {
        target: {value: "Reset Test Task"},
      });

      fireEvent.change(colorInput, {
        target: {value: "#ff0000"},
      });

      fireEvent.click(screen.getByRole("button", {name: "Absenden"}));

      await waitFor(() => {
        expect(colorInput.value).toBe("#ffffff");
      });
    });
  });
