import { describe, it, expect } from 'vitest'
import { priorityToColor, priorityToLabel } from './priorityUtils'

describe('priorityToColor', () => {
    it('gibt rot für HIGH zurück', () => {
        expect(priorityToColor('HIGH')).toBe('#ff0000')
    })

    it('gibt gelb für MEDIUM zurück', () => {
        expect(priorityToColor('MEDIUM')).toBe('#ffff00')
    })

    it('gibt grün für LOW zurück', () => {
        expect(priorityToColor('LOW')).toBe('#00ff00')
    })

    it('gibt weiss für unbekannte Priorität zurück', () => {
        expect(priorityToColor('UNKNOWN')).toBe('#ffffff')
    })

    it('gibt weiss für undefined zurück', () => {
        expect(priorityToColor(undefined)).toBe('#ffffff')
    })
})

describe('priorityToLabel', () => {
    it('gibt Hoch für HIGH zurück', () => {
        expect(priorityToLabel('HIGH')).toBe('Hoch')
    })

    it('gibt Mittel für MEDIUM zurück', () => {
        expect(priorityToLabel('MEDIUM')).toBe('Mittel')
    })

    it('gibt Tief für LOW zurück', () => {
        expect(priorityToLabel('LOW')).toBe('Tief')
    })

    it('gibt den Originalwert für unbekannte Priorität zurück', () => {
        expect(priorityToLabel('UNKNOWN')).toBe('UNKNOWN')
    })
})