export interface IDecreeType {
  id?: number;
  name?: string | null;
  serialNo?: string | null;
}

export class DecreeType implements IDecreeType {
  constructor(public id?: number, public name?: string | null, public serialNo?: string | null) {}
}

export function getDecreeTypeIdentifier(decreeType: IDecreeType): number | undefined {
  return decreeType.id;
}
