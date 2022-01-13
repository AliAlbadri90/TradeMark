export interface IDecreeCategory {
  id?: number;
  name?: string | null;
  serialNo?: string | null;
}

export class DecreeCategory implements IDecreeCategory {
  constructor(public id?: number, public name?: string | null, public serialNo?: string | null) {}
}

export function getDecreeCategoryIdentifier(decreeCategory: IDecreeCategory): number | undefined {
  return decreeCategory.id;
}
