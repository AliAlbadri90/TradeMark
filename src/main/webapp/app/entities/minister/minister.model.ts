export interface IMinister {
  id?: number;
  name?: string | null;
  serialNo?: string | null;
  jobTitle?: string | null;
  decreeCount?: number | null;
}

export class Minister implements IMinister {
  constructor(
    public id?: number,
    public name?: string | null,
    public serialNo?: string | null,
    public jobTitle?: string | null,
    public decreeCount?: number | null
  ) {}
}

export function getMinisterIdentifier(minister: IMinister): number | undefined {
  return minister.id;
}
