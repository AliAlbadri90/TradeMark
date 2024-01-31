export interface IRegulation {
  id?: number;
  title?: string | null;
  description?: string | null;
  type?: string | null;
  year?: number | null;
  filePdfContentType?: string | null;
  filePdf?: string | null;
  filePdfUrl?: string | null;
  fileWordContentType?: string | null;
  fileWord?: string | null;
  fileWordUrl?: string | null;
  isActive?: boolean | null;
}

export class Regulation implements IRegulation {
  constructor(
    public id?: number,
    public title?: string | null,
    public description?: string | null,
    public type?: string | null,
    public year?: number | null,
    public filePdfContentType?: string | null,
    public filePdf?: string | null,
    public filePdfUrl?: string | null,
    public fileWordContentType?: string | null,
    public fileWord?: string | null,
    public fileWordUrl?: string | null,
    public isActive?: boolean | null
  ) {
    this.isActive = this.isActive ?? false;
  }
}

export function getRegulationIdentifier(regulation: IRegulation): number | undefined {
  return regulation.id;
}
