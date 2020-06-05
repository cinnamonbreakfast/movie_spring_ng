
export class Sort {    
    fields: Map<string, string>;

    constructor() {
        this.fields = new Map<string, string>();
    }

    and(field: string, direction: string): Sort {
        this.fields.set(field, direction);
        return this;
    }

    getMap(){
        const convMap = [];
        this.fields.forEach((val: string, key: string) => {
            // convMap[key] = val;
            convMap.push(key);
            convMap.push(val);
        })

        return convMap;
    }

    clear(): void {
        this.fields.clear();
    }
}

export namespace Sort {
    export enum Direction {
        ASC = "a",
        DESC = "d"
    }
}
