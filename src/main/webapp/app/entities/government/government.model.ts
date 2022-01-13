export interface IGovernment {
  id?: number;
  name?: string | null;
  serialNo?: string | null;
}

export class Government implements IGovernment {
  constructor(public id?: number, public name?: string | null, public serialNo?: string | null) {}
}

export function getGovernmentIdentifier(government: IGovernment): number | undefined {
  return government.id;
}
